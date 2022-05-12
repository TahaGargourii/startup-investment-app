package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.InvestorRequest;

public interface InvestorService {

    ApiResponse getAllInvestors();

    ApiResponse getInvestorById(long id);

    ApiResponse createInvestor(InvestorRequest investorRequest);

    ApiResponse updateInvestor(long id, InvestorRequest investorRequest);

    ApiResponse deleteInvestor(long id);

    ApiResponse deleteAllInvestors();
}