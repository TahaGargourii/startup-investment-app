package design.startupInvestment.springboot.controller;

import design.startupInvestment.springboot.model.User;
import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
import design.startupInvestment.springboot.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
/*@RequestMapping("/register")*/
public class RegistrationController {

	private final UserService userService;

/*	@PostMapping*/
	public ResponseEntity<User> registrationRequest(@Valid @RequestBody RegistrationRequest registrationRequest) {

		final User registrationResponse = userService.registration(registrationRequest);

		return ResponseEntity.status(HttpStatus.CREATED).body(registrationResponse);
	}

}
