package design.startupInvestment.springboot.security.dto.request;

import lombok.AllArgsConstructor;
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



