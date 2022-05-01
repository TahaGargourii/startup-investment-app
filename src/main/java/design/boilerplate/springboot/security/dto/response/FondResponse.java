package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Startup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FondResponse {

	private Long id;

	private int amount;

	private String type;

	private String capTable;


	private StartupResponse startup;

	private InvestorResponse investor;

}
