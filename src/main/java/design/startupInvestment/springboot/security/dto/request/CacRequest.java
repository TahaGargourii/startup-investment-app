package design.startupInvestment.springboot.security.dto.request;

import design.startupInvestment.springboot.model.Startup;
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
public class CacRequest {



	int amount;

	String month;

	String year;

	private long startupId;

	}



