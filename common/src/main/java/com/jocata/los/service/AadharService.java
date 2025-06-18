package com.jocata.los.service;

import com.jocata.los.response.AadharDetails;
import com.jocata.los.response.AadharResponseForm;

public interface AadharService {
    AadharResponseForm getAadharDetailsByAadharNumber(String aadharNumber);
}
