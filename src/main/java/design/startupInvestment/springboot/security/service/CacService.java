package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.CacRequest;

public interface CacService {


    ApiResponse createCac(CacRequest cacRequest);

    ApiResponse getAllCacsByStartup(long startupId);

    ApiResponse getAllCacsByStartupAndMonth(long startupId, String month);



}
