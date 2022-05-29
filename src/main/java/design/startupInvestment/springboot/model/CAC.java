package design.startupInvestment.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "CAC")
public class CAC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int amount;

    String month;

    String year;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "startup_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Startup startup;

}
