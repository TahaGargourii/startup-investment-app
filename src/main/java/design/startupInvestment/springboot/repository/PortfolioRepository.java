package design.startupInvestment.springboot.repository;


import design.startupInvestment.springboot.model.Investor;
import design.startupInvestment.springboot.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


   Portfolio findByInvestor(Investor investor);

}
