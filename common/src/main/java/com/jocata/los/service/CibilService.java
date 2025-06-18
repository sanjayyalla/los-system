package com.jocata.los.service;

import com.jocata.los.response.CreditReportDTO;

public interface CibilService {
    CreditReportDTO getCibilDetailsByPanNumber(String panNumber);
}
