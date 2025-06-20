package com.jocata.los.service;

import com.jocata.los.response.CreditReportDTO;
import com.jocata.los.response.updateCibilData.CreditReportDTOForCibilUpdate;

public interface CibilService {
    CreditReportDTO getCibilDetailsByPanNumber(String panNumber);

    CreditReportDTOForCibilUpdate postCibilDetails(CreditReportDTOForCibilUpdate creditReportDTO);
}
