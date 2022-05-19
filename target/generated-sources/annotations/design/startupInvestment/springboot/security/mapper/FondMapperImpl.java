package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Fond;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.request.FondRequest;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.dto.response.FondResponse;
import design.startupInvestment.springboot.security.dto.response.InvestorResponse;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import design.startupInvestment.springboot.security.dto.response.StartupResponse;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import design.startupInvestment.springboot.security.dto.response.UserResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:48+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class FondMapperImpl implements FondMapper {

    @Override
    public FondResponse convertToFondResponse(Fond fond) {
        if ( fond == null ) {
            return null;
        }

        FondResponse fondResponse = new FondResponse();

        fondResponse.setId( fond.getId() );
        fondResponse.setAmount( fond.getAmount() );
        fondResponse.setType( fond.getType() );
        fondResponse.setCapTable( fond.getCapTable() );
        fondResponse.setStartup( startupToStartupResponse( fond.getStartup() ) );
        fondResponse.setInvestor( investorToInvestorResponse( fond.getInvestor() ) );

        return fondResponse;
    }

    @Override
    public Fond convertToFond(FondRequest fondRequest) {
        if ( fondRequest == null ) {
            return null;
        }

        Fond fond = new Fond();

        fond.setAmount( fondRequest.getAmount() );
        fond.setType( fondRequest.getType() );
        fond.setCapTable( fondRequest.getCapTable() );

        return fond;
    }

    protected UserResponse userToUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setName( user.getName() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setUserRole( user.getUserRole() );

        return userResponse;
    }

    protected StartupperResponse startupperToStartupperResponse(Startupper startupper) {
        if ( startupper == null ) {
            return null;
        }

        StartupperResponse startupperResponse = new StartupperResponse();

        startupperResponse.setId( startupper.getId() );
        startupperResponse.setUser( userToUserResponse( startupper.getUser() ) );

        return startupperResponse;
    }

    protected StartupResponse startupToStartupResponse(Startup startup) {
        if ( startup == null ) {
            return null;
        }

        StartupResponse startupResponse = new StartupResponse();

        startupResponse.setId( startup.getId() );
        startupResponse.setName( startup.getName() );
        startupResponse.setStartupper( startupperToStartupperResponse( startup.getStartupper() ) );

        return startupResponse;
    }

    protected RegistrationRequest userToRegistrationRequest(User user) {
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

    protected InvestorResponse investorToInvestorResponse(Investor investor) {
        if ( investor == null ) {
            return null;
        }

        InvestorResponse investorResponse = new InvestorResponse();

        investorResponse.setId( investor.getId() );
        investorResponse.setInvestingStages( investor.getInvestingStages() );
        investorResponse.setTicketSize( investor.getTicketSize() );
        investorResponse.setUser( userToRegistrationRequest( investor.getUser() ) );
        investorResponse.setPortfolio( portfolioToPortfolioResponse( investor.getPortfolio() ) );

        return investorResponse;
    }
}
