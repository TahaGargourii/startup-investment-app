package design.startupInvestment.springboot.security.service;

import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.AuthenticatedUserDto;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

    User findByUsername(String username);

    User registration(RegistrationRequest registrationRequest);

   /// AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

    User getConnectedInvestor();

    User getConnectedStartupper();

}
