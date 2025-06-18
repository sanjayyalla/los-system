package com.jocata.los.dao;

import com.jocata.los.entity.AadharDetails;
import com.jocata.los.entity.CibilDetails;
import com.jocata.los.entity.PanDetails;

public interface DataVerificationDao {

    PanDetails savePanDetails(PanDetails panDetails);

    AadharDetails saveAadharDetails(AadharDetails aadharDetails);

    CibilDetails saveCibilDetails(CibilDetails cibilDetails);
}
