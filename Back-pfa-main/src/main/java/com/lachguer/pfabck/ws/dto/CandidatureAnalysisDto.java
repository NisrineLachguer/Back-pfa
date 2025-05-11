package com.lachguer.pfabck.ws.dto;

public class CandidatureAnalysisDto {
    private String analysis;
    private String matchPercentage;
    private boolean isAccepted;
    private Integer percentageValue;

    public CandidatureAnalysisDto( String analysis, String matchPercentage, boolean isAccepted, Integer percentageValue) {
        this.analysis = analysis;
        this.matchPercentage = matchPercentage;
        this.isAccepted = isAccepted;
        this.percentageValue = percentageValue;
    }

    public CandidatureAnalysisDto(String analysis, String matchPercentage, boolean isAccepted ) {
        this.analysis = analysis;
        this.matchPercentage = matchPercentage;
        this.isAccepted = isAccepted;
        // Extraire la valeur numérique du pourcentage
        if (matchPercentage != null && matchPercentage.endsWith("%")) {
            try {
                String numStr = matchPercentage.substring(0, matchPercentage.length() - 1);
                this.percentageValue = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                this.percentageValue = 0;
            }
        } else {
            this.percentageValue = 0;
        }
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(String matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public Integer getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(Integer percentageValue) {
        this.percentageValue = percentageValue;
    }
}
