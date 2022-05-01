package design.boilerplate.springboot.repository;
import design.boilerplate.springboot.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

	
	public interface TeamRepository extends JpaRepository<Team, Long> {

/*		Team findByname(String username);
		Team findByMemebers (String Membername) ;
		Team findByField(String field) ;
		Team findById(int id) ;
		boolean existsByEmail(String email);
		

		boolean existsByUsername(String username);*/

	}



