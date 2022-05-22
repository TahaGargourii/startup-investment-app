package design.startupInvestment.springboot.security.dto.request;

import design.startupInvestment.springboot.model.Startup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@NoArgsConstructor
public class IncomeRequest {


	private String libelle;

	private long amount;

	private Date creation;

	private long startup_id;

	}



