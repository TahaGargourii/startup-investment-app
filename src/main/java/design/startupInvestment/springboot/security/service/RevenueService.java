package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.RevenueRequest;

public interface RevenueService {

    ApiResponse getAllRevenuesByStartup(long startupId);

    ApiResponse getAllRevenuesByStartupAndYear(long startupId, String year);

    ApiResponse getRevenueById(long id);

    ApiResponse createRevenue(RevenueRequest revenueRequest);


}
