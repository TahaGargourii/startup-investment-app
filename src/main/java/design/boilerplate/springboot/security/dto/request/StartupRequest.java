package design.boilerplate.springboot.security.dto.request;

import design.boilerplate.springboot.model.Startupper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@NoArgsConstructor
public class StartupRequest {

	@NotEmpty(message = "{startupName_not_empty}")
	private String startupName;

	private StartupperRequest startupper;

	}



