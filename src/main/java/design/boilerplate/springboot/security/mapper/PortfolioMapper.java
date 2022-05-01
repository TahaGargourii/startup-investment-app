package design.boilerplate.springboot.security.mapper;

import design.boilerplate.springboot.model.Portfolio;
import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.User;
import design.boilerplate.springboot.security.dto.request.PortfolioRequest;
import design.boilerplate.springboot.security.dto.request.InvestorRequest;
import design.boilerplate.springboot.security.dto.request.RegistrationRequest;
import design.boilerplate.springboot.security.dto.response.PortfolioResponse;
import design.boilerplate.springboot.security.dto.response.InvestorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PortfolioMapper {

    PortfolioMapper INSTANCE = Mappers.getMapper(PortfolioMapper.class);

    PortfolioResponse convertToPortfolioResponse(Portfolio portfolio);

    Portfolio convertToPortfolio(PortfolioRequest portfolioRequest);
}
