package design.startupInvestment.springboot.security.dto.response;


import design.startupInvestment.springboot.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String username;

    private String email;

    private UserRole userRole;

}


