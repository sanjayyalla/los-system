package com.lossystem.los.service;

import com.lossystem.los.response.PanResponseForm;

public interface PanService {
    PanResponseForm getPanDetailsByPanNumber(String panNumber);
}
