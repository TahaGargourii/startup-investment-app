package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Fond;
import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FondRepository extends JpaRepository<Fond, Long> {

	List<Fond> findFondByInvestor(Investor investorId);
	List<Fond> findFondByStartup(Startup startupId) ;

}

