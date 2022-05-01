package design.boilerplate.springboot.repository;


import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {


   Portfolio findByInvestor(Investor investor);

}
