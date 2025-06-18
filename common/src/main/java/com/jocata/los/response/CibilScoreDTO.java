package com.jocata.los.response;
public class CibilScoreDTO {
    private String score;
    private String scoreDate;
    private String riskGrade;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public String getRiskGrade() {
        return riskGrade;
    }

    public void setRiskGrade(String riskGrade) {
        this.riskGrade = riskGrade;
    }
}
