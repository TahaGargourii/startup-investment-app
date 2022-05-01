package design.boilerplate.springboot.security.dto.response;


import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Startupper;
import design.boilerplate.springboot.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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


