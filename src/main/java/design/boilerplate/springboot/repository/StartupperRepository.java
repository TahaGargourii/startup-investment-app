package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StartupperRepository extends JpaRepository<Startupper, Long> {


}
