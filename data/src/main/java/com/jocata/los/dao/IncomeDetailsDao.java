package com.jocata.los.dao;

import com.jocata.los.entity.IncomeDetails;

public interface IncomeDetailsDao {

    IncomeDetails saveIncomeDetails(IncomeDetails incomeDetails);

    IncomeDetails getIncomeDetails(Integer incomeId);

}
