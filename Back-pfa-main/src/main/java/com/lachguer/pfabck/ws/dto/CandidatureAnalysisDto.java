package com.lachguer.pfabck.ws.dto;

public class CandidatureAnalysisDto {
    private String analysis;
    private String matchPercentage;
    private boolean isAccepted;

    public CandidatureAnalysisDto() {}

    public CandidatureAnalysisDto(String analysis, String matchPercentage, boolean isAccepted) {
        this.analysis = analysis;
        this.matchPercentage = matchPercentage;
        this.isAccepted = isAccepted;
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
}
