package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {


}



