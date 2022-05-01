package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.Investor;
import design.boilerplate.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvestorRepository extends JpaRepository<Investor, Long> {
	
	Investor findByUser(User user) ;
	/*Investor findByUsername (String name) ;
	List <Investor >findByInvestingstages (String Investing_stage) ;
	List <Investor>  findByTicketSize (float Ticket_Size) ;*/
	
	
	


}
