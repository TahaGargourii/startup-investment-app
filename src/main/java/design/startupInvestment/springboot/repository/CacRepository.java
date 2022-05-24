package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.CAC;
import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CacRepository extends JpaRepository<CAC, Long> {

    List<CAC> findByStartup(Startup startup);


    List<CAC> findByStartupAndMonth(Startup startup, String month);


}



