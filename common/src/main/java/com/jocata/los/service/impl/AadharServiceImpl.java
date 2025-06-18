package com.jocata.los.service.impl;

import com.jocata.los.response.AadharDetails;
import com.jocata.los.response.AadharResponseForm;
import com.jocata.los.service.AadharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AadharServiceImpl implements AadharService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AadharResponseForm getAadharDetailsByAadharNumber(String aadharNumber) {
        String url = "http://localhost:8081/aadharservice/api/v1/getAadharDetails?aadharNumber=" + aadharNumber;
        AadharResponseForm res = restTemplate.getForEntity(url, AadharResponseForm.class).getBody();
        return res;

    }
}
