package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.IncomeRequest;

public interface IncomeService {

    ApiResponse getAllIncomes();

    ApiResponse getIncomeById(long id);

    ApiResponse createIncome(IncomeRequest incomeRequest);

    ApiResponse updateIncome(long id, IncomeRequest incomeRequest);

    ApiResponse deleteIncome(long id);

    ApiResponse deleteAllIncomes();

}
