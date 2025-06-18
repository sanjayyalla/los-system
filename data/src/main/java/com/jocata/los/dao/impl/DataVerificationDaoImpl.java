package com.jocata.los.dao.impl;

import com.jocata.los.dao.DataVerificationDao;
import com.jocata.los.entity.AadharDetails;
import com.jocata.los.entity.CibilDetails;
import com.jocata.los.entity.PanDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class DataVerificationDaoImpl implements DataVerificationDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public PanDetails savePanDetails(PanDetails panDetails) {
        entityManager.persist(panDetails);
        entityManager.flush();
        entityManager.refresh(panDetails);
        return panDetails;
    }

    @Override
    public AadharDetails saveAadharDetails(AadharDetails aadharDetails) {
        entityManager.persist(aadharDetails);
        entityManager.flush();
        entityManager.refresh(aadharDetails);
        return aadharDetails;
    }

    @Override
    public CibilDetails saveCibilDetails(CibilDetails cibilDetails) {
        entityManager.persist(cibilDetails);
        entityManager.flush();
        entityManager.refresh(cibilDetails);
        return cibilDetails;
    }
}
