package design.startupInvestment.springboot.security.service.impl;

import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.model.UserRole;
import design.startupInvestment.springboot.repository.UserRepository;
import design.startupInvestment.springboot.security.dto.AuthenticatedUserDto;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.mapper.UserMapper;
import design.startupInvestment.springboot.security.service.UserService;
import design.startupInvestment.springboot.service.UserValidationService;
import design.startupInvestment.springboot.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(null, REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return user;
	}

	/*@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}*/

	@Override
	public User getConnectedInvestor() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
		if (user != null) {
			if (user.getInvestor() != null && user.getUserRole().equals(UserRole.INVESTOR)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User getConnectedStartupper() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsername(((UserDetails) principal).getUsername());
		if (user != null) {
			if (user.getStartupper() != null && user.getUserRole().equals(UserRole.STARTUPPER)) {
				return user;
			}
		}
		return null;
	}
}
