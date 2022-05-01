package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.model.Startup;
import design.boilerplate.springboot.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartupperResponse {


	private Long id;

	private UserResponse user;

	private StartupResponse startup;

	private FileResponse files;

}
