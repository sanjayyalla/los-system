package com.lossystem.los.service;

import com.lossystem.los.response.AadharDetails;
import com.lossystem.los.response.AadharResponseForm;

public interface AadharService {
    AadharResponseForm getAadharDetailsByAadharNumber(String aadharNumber);
}
