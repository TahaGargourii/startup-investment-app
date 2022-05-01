package design.boilerplate.springboot.security.dto.request;

import design.boilerplate.springboot.model.Portfolio;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestorRequest {

		@NotEmpty(message = "{investingStages_not_empty}")
		private String investingStages;

		@NotEmpty(message = "{ticketSize_not_empty}")
		private int ticketSize;

		@NotEmpty(message = "{user_not_empty}")
		private RegistrationRequest user;

		private PortfolioRequest portfolio;

}



