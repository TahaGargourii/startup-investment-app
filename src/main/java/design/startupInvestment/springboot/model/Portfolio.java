package design.startupInvestment.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "PORTFOLIO")
public class Portfolio {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int size ;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "investor_id", referencedColumnName = "id")
	private Investor investor;

}
