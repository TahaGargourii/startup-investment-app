package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Startup;
import design.startupInvestment.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StartupRepository extends JpaRepository<Startup, Long> {

    Startup findStartupByStartupper(Startupper startupper);

}
