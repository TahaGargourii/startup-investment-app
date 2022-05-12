package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Fond;
import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FondRepository extends JpaRepository<Fond, Long> {

    List<Fond> findFondByInvestor(Investor investor);

    List<Fond> findFondByStartup(Optional<Startup> startup);

    Fond findFondByIdAndInvestor(Long id, Investor investor);

    Fond findFondByIdAndStartup(Long id, Startup startup);

}

