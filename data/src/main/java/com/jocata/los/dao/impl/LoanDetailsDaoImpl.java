package com.jocata.los.dao.impl;

import com.jocata.los.dao.LoanDetailsDao;
import com.jocata.los.entity.LoanApplication;
import com.jocata.los.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@Transactional
public class LoanDetailsDaoImpl implements LoanDetailsDao {


    @Autowired
    private EntityManager entityManager;

    @Override
    public LoanApplication saveLoanApplication(LoanApplication loanApplication) {
        if (loanApplication.getApplicationId() == null) {
            entityManager.persist(loanApplication);
        } else {
            loanApplication = entityManager.merge(loanApplication);
        }
        return loanApplication;
    }

    @Override
    public LoanApplication getLoanApplicationById(Integer applicationId) {
        return entityManager.find(LoanApplication.class, applicationId);
    }

    @Override
    public LoanApplication updateLoanApplication(Integer loanApplicationId, BigDecimal approvedAmount, Status loanStatus) {
        LoanApplication loanApplication = entityManager.find(LoanApplication.class, loanApplicationId);
        if (loanApplication != null) {
            loanApplication.setApprovalAmount(approvedAmount);
            loanApplication.setStatus(loanStatus);
        }
        return loanApplication;


    }
}
