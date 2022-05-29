package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.CAC;
import design.startupInvestment.springboot.model.Revenue;
import design.startupInvestment.springboot.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    List<Revenue> findByStartup(Startup startup);


    List<Revenue> findByStartupAndMonth(Startup startup, String month);
    List<Revenue> findByStartupAndYear(Startup startup, String year);
}



