package design.startupInvestment.springboot.security.dto.response;

import design.startupInvestment.springboot.security.dto.request.RegistrationRequest;
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



}
