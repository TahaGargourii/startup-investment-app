package design.startupInvestment.springboot.exceptions;

import design.startupInvestment.springboot.controller.LoginController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@RestControllerAdvice(basePackageClasses = LoginController.class)
public class LoginControllerAdvice {

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ApiResponse> handleRegistrationException(BadCredentialsException exception) {

        final ApiResponse response = new ApiResponse(null, exception.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
