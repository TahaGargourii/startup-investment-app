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
public class TeamResponse {


	private Long id;

	private String Field;

	private int  spendingAmount;
}
