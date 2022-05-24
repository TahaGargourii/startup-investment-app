package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.RevenueRequest;

public interface RevenueService {

    ApiResponse getAllRevenuesByStartup(long startupId);

    ApiResponse getAllRevenuesByStartupAndMonth(long startupId, String month);

    ApiResponse getRevenueById(long id);

    ApiResponse createRevenue(RevenueRequest revenueRequest);


}
