package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Portfolio;
import design.boilerplate.springboot.security.dto.request.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {

	private Long id;

	private int size ;

	//private InvestorResponse investor;


}
