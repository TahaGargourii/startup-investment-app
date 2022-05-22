package design.startupInvestment.springboot.security.dto;

import design.startupInvestment.springboot.model.Admin;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Startupper;
import design.startupInvestment.springboot.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {

	private String id;

	private String name;

	private String username;

	private String password;

	private UserRole userRole;

	private Startupper startupper;

	private Investor investor;

	private Admin admin;

}
