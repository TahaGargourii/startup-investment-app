package design.startupInvestment.springboot.security.dto.response;

import design.startupInvestment.springboot.model.Startup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponse {

	private Long id;

	private String libelle;

	private long amount;

	private Date creation;

	private Startup startup;
}
