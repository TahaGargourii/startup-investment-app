package design.startupInvestment.springboot.security.dto.response;

import design.startupInvestment.springboot.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private UserResponse user;

}
