package com.lossystem.los.dao;

import com.lossystem.los.entity.IncomeDetails;

public interface IncomeDetailsDao {

    IncomeDetails saveIncomeDetails(IncomeDetails incomeDetails);

    IncomeDetails getIncomeDetails(Integer incomeId);

}
