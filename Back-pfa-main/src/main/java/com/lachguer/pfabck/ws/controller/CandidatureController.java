package com.lachguer.pfabck.ws.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.service.CandidatureService;
import com.lachguer.pfabck.ws.converter.CandidatureConverter;
import com.lachguer.pfabck.ws.dto.CandidatureDto;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;

@RestController
@RequestMapping("/api/v1/candidatures")
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private CandidatureConverter candidatureConverter;

    @GetMapping("/{id}")
    public CandidatureDto getCandidatureById(@PathVariable Long id) {
        return candidatureConverter.toDto(candidatureService.findById(id));
    }

    @GetMapping("/")
    public List<CandidatureDto> getAllCandidatures() {
        return candidatureConverter.toDtos(candidatureService.findAll());
    }

    @PostMapping("/")
    public CandidatureDto saveCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.save(candidatureConverter.toItem(candidatureDto)));
    }

    @PutMapping("/")
    public CandidatureDto updateCandidature(@RequestBody CandidatureDto candidatureDto) {
        return candidatureConverter.toDto(candidatureService.update(candidatureConverter.toItem(candidatureDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteCandidature(@PathVariable Long id) {
        candidatureService.delete(id);
    }


    @GetMapping("/recruteur/{recruteurId}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<List<CandidatureDto>> getCandidaturesByRecruteur(
            @PathVariable Long recruteurId) {
        List<Candidature> candidatures = candidatureService.findByRecruteurId(recruteurId);

        // Chargez les données associées
        candidatures.forEach(c -> {
            c.getCandidat(); // Charge le candidat (si lazy loading)
            c.getOffre();    // Charge l'offre (si lazy loading)
        });

        return ResponseEntity.ok(candidatureConverter.toDtos(candidatures));
    }

    //generation pdf
    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> generateCv(@PathVariable Long id) {
        LOGGER.info("Generating CV for candidature ID: " + id);
        try {
            Candidature candidature = candidatureService.findById(id);
            if (candidature == null) {
                LOGGER.info("Candidature not found with ID: " + id);
                return ResponseEntity.notFound().build();
            }

            // Log candidature data for debugging
            LOGGER.info("Candidature found: " + candidature.getId() +
                    ", Nom: " + candidature.getNom() +
                    ", Prénom: " + candidature.getPrenom());

            // Chargez explicitement les relations
            if (candidature.getCandidat() != null) {
                Hibernate.initialize(candidature.getCandidat());
                LOGGER.info("Candidat initialized: " + candidature.getCandidat().getEmail());
            } else {
                LOGGER.info("Candidat is null for candidature: " + id);
            }

            if (candidature.getOffre() != null) {
                Hibernate.initialize(candidature.getOffre());
                LOGGER.info("Offre initialized: " + candidature.getOffre().getId());
            } else {
                LOGGER.info("Offre is null for candidature: " + id);
            }

            byte[] pdfBytes = generatePdfFromCandidature(candidature);
            LOGGER.info("PDF generated, size: " + pdfBytes.length + " bytes");

            if (pdfBytes.length == 0) {
                LOGGER.info("Generated PDF has zero bytes!");
                return ResponseEntity.internalServerError()
                        .body("PDF générée est vide".getBytes(StandardCharsets.UTF_8));
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.builder("attachment")
                            .filename("CV_" + id + ".pdf")
                            .build());

            LOGGER.info("Returning PDF response with headers: " + headers);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (Exception e) {
            LOGGER.info("Error generating PDF: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(("Erreur lors de la génération du PDF: " + e.getMessage()).getBytes(StandardCharsets.UTF_8));
        }
    }

    private byte[] generatePdfFromCandidature(Candidature candidature) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = null;

        try {
            LOGGER.info("Starting PDF generation");
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Définir une police par défaut
            Font defaultFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);

            // Titre
            document.add(new Paragraph("CV Candidature", titleFont));
            document.add(Chunk.NEWLINE);

            // Section Informations Personnelles
            document.add(new Paragraph("Informations Personnelles", sectionFont));
            addField(document, "Nom", candidature.getNom(), defaultFont);
            addField(document, "Prénom", candidature.getPrenom(), defaultFont);

            String email = "";
            if (candidature.getCandidat() != null) {
                email = candidature.getCandidat().getEmail();
            }
            addField(document, "Email", email, defaultFont);

            addField(document, "Téléphone", candidature.getTelephone(), defaultFont);
            document.add(Chunk.NEWLINE);

            // Autres sections avec vérification des null
            addSection(document, "Formation", candidature.getFormation(), sectionFont, defaultFont);
            addSection(document, "Expérience", candidature.getExperience(), sectionFont, defaultFont);
            addSection(document, "Compétences", candidature.getCompetences(), sectionFont, defaultFont);
            addSection(document, "Motivation", candidature.getMotivation(), sectionFont, defaultFont);
            addSection(document, "Disponibilité", candidature.getDisponibilite(), sectionFont, defaultFont);

            document.close();
            writer.close();

            byte[] result = outputStream.toByteArray();
            LOGGER.info("PDF generation completed, size: " + result.length);
            return result;
        } finally {
            try {
                if (document != null && document.isOpen()) {
                    document.close();
                }
                if (writer != null) {
                    writer.close();
                }
                outputStream.close();
            } catch (Exception e) {
                LOGGER.info("Error closing PDF resources: " + e.getMessage());
            }
        }
    }

    private void addField(Document document, String label, String value, Font font) throws DocumentException {
        if (value != null && !value.isEmpty()) {
            document.add(new Paragraph(label + ": " + value, font));
        } else {
            // Add field even if empty to ensure document isn't empty
            document.add(new Paragraph(label + ": Non spécifié", font));
        }
    }

    private void addSection(Document document, String title, String content, Font titleFont, Font contentFont)
            throws DocumentException {
        document.add(new Paragraph(title, titleFont));
        if (content != null && !content.isEmpty()) {
            document.add(new Paragraph(content, contentFont));
        } else {
            document.add(new Paragraph("Information non disponible", contentFont));
        }
        document.add(Chunk.NEWLINE);
    }



    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<CandidatureDto> updateCandidatureStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusUpdate) {

        LOGGER.info("Updating status for candidature ID: " + id);
        LOGGER.info("New status: " + statusUpdate.get("statut"));

        String newStatus = statusUpdate.get("statut");
        if (newStatus == null || !Candidature.isValidStatus(newStatus)) {
            LOGGER.warn("Invalid status provided: " + newStatus);
            return ResponseEntity.badRequest().build();
        }

        try {
            Candidature updated = candidatureService.updateStatus(id, newStatus);
            if (updated == null) {
                LOGGER.warn("Candidature not found with ID: " + id);
                return ResponseEntity.notFound().build();
            }

            LOGGER.info("Status updated successfully");
            return ResponseEntity.ok(candidatureConverter.toDto(updated));
        } catch (Exception e) {
            LOGGER.error("Error updating status: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
