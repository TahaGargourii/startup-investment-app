package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.StartupperRequest;

public interface StartupperService {
    ApiResponse getAllStartuppers();

    ApiResponse getStartupperById(long id);

    ApiResponse createStartupper(StartupperRequest startupperRequest);

    ApiResponse deleteStartupper(long id);

    ApiResponse deleteAllStartuppers();


}
