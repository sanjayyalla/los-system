package com.jocata.los.response;

import java.sql.Timestamp;

public class PanResponseForm {
    private PanDetails panDetails;
    private String status;
    private String message;
    private Timestamp currentTime;

    public PanResponseForm() {

    }

    public PanResponseForm(PanDetails panDetails, String status, String message, Timestamp currentTime) {
        this.panDetails = panDetails;
        this.status = status;
        this.message = message;
        this.currentTime = currentTime;
    }

    public PanDetails getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(PanDetails panDetails) {
        this.panDetails = panDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Timestamp currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return "PanResponseForm{" +
                "panDetails=" + panDetails +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", currentTime=" + currentTime +
                '}';
    }
}

