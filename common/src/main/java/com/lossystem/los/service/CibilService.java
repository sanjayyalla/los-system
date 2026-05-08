package com.lossystem.los.service;

import com.lossystem.los.response.CreditReportDTO;
import com.lossystem.los.response.updateCibilData.CreditReportDTOForCibilUpdate;

public interface CibilService {
    CreditReportDTO getCibilDetailsByPanNumber(String panNumber);

    CreditReportDTOForCibilUpdate postCibilDetails(CreditReportDTOForCibilUpdate creditReportDTO);
}
