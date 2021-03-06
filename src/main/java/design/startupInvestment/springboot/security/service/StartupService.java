package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.security.dto.request.StartupRequest;

public interface StartupService {

    ApiResponse getAllStartups();

    ApiResponse getAllStartupsByStartupper();
    ApiResponse getStartupById(long id);
    ApiResponse getAllStartupsByPortfolio();


    ApiResponse createStartup(StartupRequest startupRequest);

    ApiResponse updateStartup(long id, StartupRequest startupRequest);

    ApiResponse deleteStartup(long id);

    ApiResponse deleteAllStartups();
}
