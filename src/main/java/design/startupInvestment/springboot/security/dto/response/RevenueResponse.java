package design.startupInvestment.springboot.security.dto.response;

import design.startupInvestment.springboot.model.Startup;
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
public class RevenueResponse {

	private Long id;

	int amount;

	String month;

	String year;

	private Startup startup;
}
