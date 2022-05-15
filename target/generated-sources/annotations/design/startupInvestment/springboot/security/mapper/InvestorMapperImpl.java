package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.User.UserBuilder;
import design.startupInvestment.springboot.security.dto.request.InvestorRequest;
import design.startupInvestment.springboot.security.dto.request.PortfolioRequest;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.dto.response.InvestorResponse;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:49+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class InvestorMapperImpl implements InvestorMapper {

    @Override
    public InvestorResponse convertToInvestorResponse(Investor investor) {
        if ( investor == null ) {
            return null;
        }

        InvestorResponse investorResponse = new InvestorResponse();

        investorResponse.setId( investor.getId() );
        investorResponse.setInvestingStages( investor.getInvestingStages() );
        investorResponse.setTicketSize( investor.getTicketSize() );
        investorResponse.setUser( convertUserToRegistrationRequest( investor.getUser() ) );
        investorResponse.setPortfolio( portfolioToPortfolioResponse( investor.getPortfolio() ) );

        return investorResponse;
    }

    @Override
    public Investor convertToInvestor(InvestorRequest investorRequest) {
        if ( investorRequest == null ) {
            return null;
        }

        Investor investor = new Investor();

        investor.setInvestingStages( investorRequest.getInvestingStages() );
        investor.setTicketSize( investorRequest.getTicketSize() );
        investor.setPortfolio( portfolioRequestToPortfolio( investorRequest.getPortfolio() ) );
        investor.setUser( registrationRequestToUser( investorRequest.getUser() ) );

        return investor;
    }

    @Override
    public Investor convertToInvestor(InvestorRequest investorRequest, User user) {
        if ( investorRequest == null && user == null ) {
            return null;
        }

        Investor investor = new Investor();

        if ( investorRequest != null ) {
            investor.setInvestingStages( investorRequest.getInvestingStages() );
            investor.setTicketSize( investorRequest.getTicketSize() );
            investor.setPortfolio( portfolioRequestToPortfolio( investorRequest.getPortfolio() ) );
            investor.setUser( registrationRequestToUser( investorRequest.getUser() ) );
        }
        if ( user != null ) {
            investor.setId( user.getId() );
        }

        return investor;
    }

    @Override
    public RegistrationRequest convertUserToRegistrationRequest(User user) {
        if ( user == null ) {
            return null;
        }

        RegistrationRequest registrationRequest = new RegistrationRequest();

        registrationRequest.setName( user.getName() );
        registrationRequest.setEmail( user.getEmail() );
        registrationRequest.setUsername( user.getUsername() );
        registrationRequest.setPassword( user.getPassword() );

        return registrationRequest;
    }

    protected PortfolioResponse portfolioToPortfolioResponse(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioResponse portfolioResponse = new PortfolioResponse();

        portfolioResponse.setId( portfolio.getId() );
        portfolioResponse.setSize( portfolio.getSize() );

        return portfolioResponse;
    }

    protected Portfolio portfolioRequestToPortfolio(PortfolioRequest portfolioRequest) {
        if ( portfolioRequest == null ) {
            return null;
        }

        Portfolio portfolio = new Portfolio();

        portfolio.setSize( portfolioRequest.getSize() );

        return portfolio;
    }

    protected User registrationRequestToUser(RegistrationRequest registrationRequest) {
        if ( registrationRequest == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.name( registrationRequest.getName() );
        user.username( registrationRequest.getUsername() );
        user.password( registrationRequest.getPassword() );
        user.email( registrationRequest.getEmail() );

        return user.build();
    }
}
