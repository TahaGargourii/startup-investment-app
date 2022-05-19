package design.startupInvestment.springboot.security.mapper;

import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.User.UserBuilder;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.dto.request.StartupRequest;
import design.startupInvestment.springboot.security.dto.request.StartupperRequest;
import design.startupInvestment.springboot.security.dto.response.StartupResponse;
import design.startupInvestment.springboot.security.dto.response.StartupperResponse;
import design.startupInvestment.springboot.security.dto.response.UserResponse;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-15T22:26:48+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_332 (Amazon.com Inc.)"
)
public class StartupMapperImpl implements StartupMapper {

    @Override
    public StartupResponse convertToStartupResponse(Startup startup) {
        if ( startup == null ) {
            return null;
        }

        StartupResponse startupResponse = new StartupResponse();

        startupResponse.setId( startup.getId() );
        startupResponse.setName( startup.getName() );
        startupResponse.setStartupper( startupperToStartupperResponse( startup.getStartupper() ) );

        return startupResponse;
    }

    @Override
    public Startup convertToStartup(StartupRequest startupRequest) {
        if ( startupRequest == null ) {
            return null;
        }

        Startup startup = new Startup();

        startup.setName( startupRequest.getName() );
        startup.setStartupper( startupperRequestToStartupper( startupRequest.getStartupper() ) );

        return startup;
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

    protected Startupper startupperRequestToStartupper(StartupperRequest startupperRequest) {
        if ( startupperRequest == null ) {
            return null;
        }

        Startupper startupper = new Startupper();

        startupper.setUser( registrationRequestToUser( startupperRequest.getUser() ) );

        return startupper;
    }
}
