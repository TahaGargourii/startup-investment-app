package design.boilerplate.springboot.controller;

import design.boilerplate.springboot.security.dto.request.LoginRequest;
import design.boilerplate.springboot.security.dto.response.InvestorResponse;
import design.boilerplate.springboot.security.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/login")
public class LoginController {

	private final JwtTokenService jwtTokenService;

	@PostMapping
	public ResponseEntity<InvestorResponse.LoginResponse> loginRequest(@Valid @RequestBody LoginRequest loginRequest) {

		final InvestorResponse.LoginResponse loginResponse = jwtTokenService.getLoginResponse(loginRequest);

		return ResponseEntity.ok(loginResponse);
	}

}
