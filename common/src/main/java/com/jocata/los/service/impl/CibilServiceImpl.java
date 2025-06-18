package com.jocata.los.service.impl;

import com.jocata.los.response.CreditReportDTO;
import com.jocata.los.service.CibilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CibilServiceImpl implements CibilService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CreditReportDTO getCibilDetailsByPanNumber(String panNumber) {
        String url = "http://localhost:8083/cibil/api/v1/getCreditReportByPan?panNumber=" + panNumber;
        CreditReportDTO creditReportDTO= restTemplate.getForEntity(url, CreditReportDTO.class).getBody();
        System.out.println(creditReportDTO);
        return creditReportDTO;
    }
}
