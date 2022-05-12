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
@AllArgsConstructor
@NoArgsConstructor
public class FondRequest {

	@NotEmpty(message = "{amount_not_empty}")
	private int amount;

	@NotEmpty(message = "{type_not_empty}")
	private String type;

	@NotEmpty(message = "{capTable_not_empty}")
	private String capTable;

	@NotEmpty(message = "{startupName_not_empty}")
	private Long startupId;

	@NotEmpty(message = "{investorName_not_empty}")
	private Long investorId;

}



