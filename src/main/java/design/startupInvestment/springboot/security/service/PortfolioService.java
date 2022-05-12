package design.startupInvestment.springboot.security.service;


import design.startupInvestment.springboot.exceptions.ApiResponse;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;

public interface PortfolioService {

    ApiResponse getAllPortfolios(String type, String capTable);

    ApiResponse getPortfolioById(long id);

    ApiResponse createPortfolio(PortfolioRequest portfolioRequest);

    ApiResponse updatePortfolio(long id, PortfolioRequest portfolioRequest);

    ApiResponse deletePortfolio(long id);

    ApiResponse deleteAllPortfolios();

    ApiResponse findByInvestor(long investorId);

}
