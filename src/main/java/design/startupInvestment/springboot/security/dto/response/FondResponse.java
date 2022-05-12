package design.startupInvestment.springboot.security.dto.response;

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
public class FondResponse {

	private Long id;

	private int amount;

	private String type;

	private String capTable;


	private StartupResponse startup;

	private InvestorResponse investor;

}
