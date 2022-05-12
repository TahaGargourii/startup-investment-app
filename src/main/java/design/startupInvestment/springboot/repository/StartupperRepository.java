package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StartupperRepository extends JpaRepository<Startupper, Long> {


}
