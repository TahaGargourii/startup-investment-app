package design.startupInvestment.springboot.repository;

import design.startupInvestment.springboot.model.File;
import design.startupInvestment.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FileRepository extends JpaRepository<File, Long> {

	List<File> findByStartupper(Startupper startupper);

	void deleteFileByIdAndStartupper(Long id, Startupper startupper);

	void deleteAllFilesByStartupper(Startupper startupper);

}
