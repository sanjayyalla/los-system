package com.jocata.los.service;

import com.jocata.los.response.PanResponseForm;

public interface PanService {
    PanResponseForm getPanDetailsByPanNumber(String panNumber);
}
