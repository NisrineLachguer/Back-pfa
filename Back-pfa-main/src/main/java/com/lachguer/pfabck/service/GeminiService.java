package com.lachguer.pfabck.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lachguer.pfabck.model.Candidature;
import com.lachguer.pfabck.model.GeminiConfig;
import com.lachguer.pfabck.model.Offre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

@Service
public class GeminiService {
    @Autowired
    private GeminiConfig geminiConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    public String analyzeCandidate(Candidature candidature, Offre offre) {
        try {
            String prompt = buildPrompt(candidature, offre);
            String apiUrl = geminiConfig.getApiUrl() + "?key=" + geminiConfig.getApiKey();

            // Création de la requête pour l'API Gemini
            ObjectNode requestBody = objectMapper.createObjectNode();
            ArrayNode contents = requestBody.putArray("contents");

            ObjectNode content = contents.addObject();
            ArrayNode parts = content.putArray("parts");

            ObjectNode part = parts.addObject();
            part.put("text", prompt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
            String response = restTemplate.postForObject(apiUrl, request, String.class);

            return extractResponseFromGemini(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'analyse des compétences: " + e.getMessage();
        }
    }

    private String buildPrompt(Candidature candidature, Offre offre) {
        return "Voici le profil d'un candidat pour un poste:\n\n" +
                "Poste: " + offre.getPosteTitre() + "\n" +
                "Description du poste: " + offre.getDescription() + "\n\n" +
                "Profil du candidat:\n" +
                "Formation: " + candidature.getFormation() + "\n" +
                "Expérience: " + candidature.getExperience() + "\n" +
                "Compétences: " + candidature.getCompetences() + "\n" +
                "Motivation: " + candidature.getMotivation() + "\n\n" +
                "En vous basant sur les compétences demandées dans la description du poste et le profil du candidat, " +
                "évaluez si ce candidat correspond aux exigences du poste. " +
                "Donnez un pourcentage de correspondance et expliquez brièvement en quoi ce candidat " +
                "correspond ou ne correspond pas au poste. Limitez votre réponse à 200 caractères maximum.";
    }

    private String extractResponseFromGemini(String jsonResponse) {
        try {
            System.out.println("Réponse brute de Gemini: " + jsonResponse);
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode candidates = root.path("candidates");

            if (candidates.isArray() && candidates.size() > 0) {
                JsonNode content = candidates.get(0).path("content");
                JsonNode parts = content.path("parts");

                if (parts.isArray() && parts.size() > 0) {
                    return parts.get(0).path("text").asText();
                }
            }

            return "Aucune réponse pertinente reçue de l'IA.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'extraction de la réponse: " + e.getMessage();
        }
    }
}
