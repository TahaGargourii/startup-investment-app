package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
