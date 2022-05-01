package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.model.Startupper;
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
public class StartupResponse {


	private Long id;

	private String name;

	private StartupperResponse startupper;

	private TeamResponse teamResponse;
}
