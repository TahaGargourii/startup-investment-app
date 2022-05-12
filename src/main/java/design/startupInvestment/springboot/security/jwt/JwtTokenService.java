package design.startupInvestment.springboot.security.jwt;

import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.AuthenticatedUserDto;
import design.startupInvestment.springboot.security.dto.request.LoginRequest;
import design.startupInvestment.springboot.security.dto.response.LoginResponse;
import design.startupInvestment.springboot.security.mapper.UserMapper;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);
		final String token = jwtTokenManager.generateToken(user);
		LoginResponse loginResponse = new LoginResponse(token, user);

		log.info(" {} has successfully logged in!", user.getUsername());

		return loginResponse;
	}

}
