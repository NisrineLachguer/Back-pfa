package com.lachguer.pfabck.ws.controller;

import com.lachguer.pfabck.model.Candidat;
import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.model.Offre;
import com.lachguer.pfabck.model.User;
import com.lachguer.pfabck.service.*;
import com.lachguer.pfabck.ws.dto.CandidatureAnalysisDto;
import com.lachguer.pfabck.ws.dto.CandidatureFormDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/candidature")
public class CandidatureApplicationController {

    @Autowired
    private CandidatService candidatService;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private OffreService offreService;

    @Autowired
    private GeminiService geminiService;


    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(@RequestBody CandidatureFormDto formDto) {
        try {
            // Validation des données requises
            if (formDto.getOffreId() == null) {
                return ResponseEntity.badRequest().body("L'ID de l'offre ne peut pas être null");
            }

            if (formDto.getEmail() == null || formDto.getEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("L'email ne peut pas être null ou vide");
            }
            // 1. Vérifier si le candidat existe déjà (par email)
            Candidat candidat = candidatService.findByEmail(formDto.getEmail());

            // 2. Si le candidat n'existe pas, le créer
            if (candidat == null) {
                candidat = new Candidat();
                candidat.setEmail(formDto.getEmail());
                candidat.setUsername(formDto.getEmail()); // Utilisez l'email comme nom d'utilisateur par défaut
                candidat.setNom(formDto.getNom());     // Assurez-vous que ces méthodes existent dans User
                candidat.setPrenom(formDto.getPrenom()); // Assurez-vous que ces méthodes existent dans User
                candidat.setTelephone(formDto.getTelephone());
                candidat.setStatutCV("non validé"); // Statut par défaut

                candidat = candidatService.save(candidat);
            } else {
                // Mettre à jour les informations du candidat si nécessaire
                candidat.setTelephone(formDto.getTelephone());
                candidat = candidatService.save(candidat);
            }

            // 3. Vérifier si l'offre existe
            Offre offre = offreService.findById(formDto.getOffreId());
            if (offre == null) {
                return ResponseEntity.badRequest().body("L'offre spécifiée n'existe pas");
            }

            // 4. Vérifier si le candidat a déjà postulé à cette offre (optionnel)
            if (candidat.getId() != null) {
                boolean alreadyApplied = candidatureService.hasAlreadyApplied(candidat.getId(), offre.getId());
                if (alreadyApplied) {
                    return ResponseEntity.badRequest().body("Vous avez déjà postulé à cette offre");
                }
            }

            // 5. Créer la candidature
            Candidature candidature = new Candidature();
            candidature.setCandidat(candidat);
            candidature.setOffre(offre);
            candidature.setStatut("en attente");
            candidature.setDateCandidature(new Date());

            // Ajouter les informations supplémentaires du formulaire
            candidature.setFormation(formDto.getFormation());
            candidature.setExperience(formDto.getExperience());
            candidature.setCompetences(formDto.getCompetences());
            candidature.setMotivation(formDto.getMotivation());
            candidature.setDisponibilite(formDto.getDisponibilite());

            // Ajoutez ça les informations personnelles
            candidature.setNom(formDto.getNom());
            candidature.setPrenom(formDto.getPrenom());
            candidature.setTelephone(formDto.getTelephone());


            // 6. Sauvegarder la candidature
            candidatureService.save(candidature);

            // 7. Analyser la candidature avec Gemini AI
            String analysisResult = geminiService.analyzeCandidate(candidature, offre);

            // 8. Traiter la réponse de Gemini pour extraire le pourcentage et la décision
            CandidatureAnalysisDto analysis = processAnalysisResult(analysisResult);

            // Créer une structure de réponse qui inclut à la fois le message et l'analyse
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Candidature envoyée avec succès");
            response.put("analysis", analysis); // Ajouter l'analyse à la réponse
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de l'enregistrement de la candidature: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    private CandidatureAnalysisDto processAnalysisResult(String analysisResult) {
        // Extraction du pourcentage de correspondance
        Pattern percentPattern = Pattern.compile("(\\d+)%");
        Matcher percentMatcher = percentPattern.matcher(analysisResult);

        String percentage = "N/A";
        boolean isAccepted = false;

        if (percentMatcher.find()) {
            percentage = percentMatcher.group(1) + "%";
            int percentValue = Integer.parseInt(percentMatcher.group(1));
            // On considère qu'un candidat est accepté s'il a plus de 70% de correspondance
            isAccepted = percentValue >= 70;
        }

        return new CandidatureAnalysisDto(analysisResult, percentage, isAccepted);
    }
}