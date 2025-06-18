package com.jocata.los.response;

import java.sql.Timestamp;

public class AadharResponseForm {

    private AadharDetails aadharDetails;
    private String status;
    private String message;
    private Timestamp currentTime;

    public AadharResponseForm() {

    }

    public AadharResponseForm(AadharDetails aadharDetails, String status, String message, Timestamp currentTime) {
        this.aadharDetails = aadharDetails;
        this.status = status;
        this.message = message;
        this.currentTime = currentTime;
    }

    public AadharDetails getAadharDetails() {
        return aadharDetails;
    }

    public void setAadharDetails(AadharDetails aadharDetails) {
        this.aadharDetails = aadharDetails;
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
}
