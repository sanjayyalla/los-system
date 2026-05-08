package com.lossystem.los.dao.impl;

import com.lossystem.los.dao.IncomeDetailsDao;
import com.lossystem.los.entity.IncomeDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class IncomeDetailsDaoImpl implements IncomeDetailsDao {


    @Autowired
    private EntityManager entityManager;

    @Override
    public IncomeDetails saveIncomeDetails(IncomeDetails incomeDetails) {
        if (incomeDetails.getIncomeId() == null) {
            entityManager.persist(incomeDetails);
        } else {
            incomeDetails = entityManager.merge(incomeDetails);
        }
        return incomeDetails;
    }

    @Override
    public IncomeDetails getIncomeDetails(Integer incomeId) {
        return entityManager.find(IncomeDetails.class, incomeId);
    }
}
