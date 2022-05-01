package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.security.dto.request.RegistrationRequest;
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
public class InvestorResponse {


	private Long id;

	private String investingStages;

	private int ticketSize;

	private RegistrationRequest user;

	private PortfolioResponse portfolio;


	/**
	 * Created on Ağustos, 2020
	 *
	 * @author Faruk
	 */
	@Getter
	@Setter
	@AllArgsConstructor
	public static class LoginResponse {

		private String token;

	}

	/**
	 * Created on Ağustos, 2020
	 *
	 * @author Faruk
	 */
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RegistrationResponse {

		private String message;

	}
}
