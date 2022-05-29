package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.FondRequest;
import io.swagger.annotations.Api;

public interface FondService {
    ApiResponse getAllFonds(String type, String capTable);

    ApiResponse getFondByIdAndStartupOrInvestor(long id);

    ApiResponse createFondByStartup(FondRequest fondRequest);

    ApiResponse getAllFondsByStartup(long startupId);

    ApiResponse getAllFondsByInvestor();

    ApiResponse getSommeFondsByInvestor();
}
