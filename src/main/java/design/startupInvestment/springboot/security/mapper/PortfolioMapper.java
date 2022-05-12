package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortfolioMapper {

    PortfolioMapper INSTANCE = Mappers.getMapper(PortfolioMapper.class);

    PortfolioResponse convertToPortfolioResponse(Portfolio portfolio);

    Portfolio convertToPortfolio(PortfolioRequest portfolioRequest);
}
