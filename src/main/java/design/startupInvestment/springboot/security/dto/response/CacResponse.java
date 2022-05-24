package design.startupInvestment.springboot.security.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import design.startupInvestment.springboot.model.Startup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class CacResponse {

	private Long id;

	int amount;

	String month;

	private Startup startup;
}
