package com.lossystem.los.service.impl;

import com.lossystem.los.response.PanResponseForm;
import com.lossystem.los.service.PanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PanServiceImpl implements PanService {
    @Autowired
    private RestTemplate restTemplate;

    public PanResponseForm getPanDetailsByPanNumber(String panNumber){
        String url = "http://localhost:8082/panservice/api/v1/getPanDetails?panNumber="+panNumber;
        PanResponseForm res = restTemplate.getForEntity(url,PanResponseForm.class).getBody();
        return res;
    }
}
