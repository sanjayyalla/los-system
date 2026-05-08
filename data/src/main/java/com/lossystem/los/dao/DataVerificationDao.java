package com.lossystem.los.dao;

import com.lossystem.los.entity.AadharDetails;
import com.lossystem.los.entity.CibilDetails;
import com.lossystem.los.entity.PanDetails;

public interface DataVerificationDao {

    PanDetails savePanDetails(PanDetails panDetails);

    AadharDetails saveAadharDetails(AadharDetails aadharDetails);

    CibilDetails saveCibilDetails(CibilDetails cibilDetails);
}
