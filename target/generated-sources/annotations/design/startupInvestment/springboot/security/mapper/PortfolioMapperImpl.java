package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:49+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class PortfolioMapperImpl implements PortfolioMapper {

    @Override
    public PortfolioResponse convertToPortfolioResponse(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioResponse portfolioResponse = new PortfolioResponse();

        portfolioResponse.setId( portfolio.getId() );
        portfolioResponse.setSize( portfolio.getSize() );

        return portfolioResponse;
    }

    @Override
    public Portfolio convertToPortfolio(PortfolioRequest portfolioRequest) {
        if ( portfolioRequest == null ) {
            return null;
        }

        Portfolio portfolio = new Portfolio();

        portfolio.setSize( portfolioRequest.getSize() );

        return portfolio;
    }
}
