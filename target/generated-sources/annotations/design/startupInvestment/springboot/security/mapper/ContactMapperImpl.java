package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Contact;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.request.ContactRequest;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.dto.response.ContactResponse;
import design.startupInvestment.springboot.security.dto.response.InvestorResponse;
import design.startupInvestment.springboot.security.dto.response.PortfolioResponse;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import design.startupInvestment.springboot.security.dto.response.UserResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:49+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactResponse convertToContactResponse(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactResponse contactResponse = new ContactResponse();

        contactResponse.setId( contact.getId() );
        contactResponse.setFirstName( contact.getFirstName() );
        contactResponse.setLastName( contact.getLastName() );
        contactResponse.setPhoneNumber( contact.getPhoneNumber() );
        contactResponse.setEmail( contact.getEmail() );
        contactResponse.setInvestor( investorToInvestorResponse( contact.getInvestor() ) );
        contactResponse.setStartupper( startupperToStartupperResponse( contact.getStartupper() ) );

        return contactResponse;
    }

    @Override
    public Contact convertToContact(ContactRequest contactRequest) {
        if ( contactRequest == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setFirstName( contactRequest.getFirstName() );
        contact.setLastName( contactRequest.getLastName() );
        contact.setPhoneNumber( contactRequest.getPhoneNumber() );
        contact.setEmail( contactRequest.getEmail() );

        return contact;
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
}
