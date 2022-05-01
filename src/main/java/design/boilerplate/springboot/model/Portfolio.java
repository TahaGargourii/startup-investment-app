package design.boilerplate.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;


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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "investor_id", referencedColumnName = "id")
	private Investor investor;

}
