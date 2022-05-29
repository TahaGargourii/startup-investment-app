package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Portfolio;
import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupRepository extends JpaRepository<Startup, Long> {

    List<Startup> findAllByStartupper(Startupper startupper);

    List<Startup> findAllByPortfolio(Portfolio portfolio);

    Startup findByStartupper(Startupper startupper);
}
