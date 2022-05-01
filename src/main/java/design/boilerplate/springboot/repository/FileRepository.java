package design.boilerplate.springboot.repository;

import design.boilerplate.springboot.model.File;
import design.boilerplate.springboot.model.Startupper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface FileRepository extends JpaRepository<File, Long> {

	List<File> findAllByFileName(String FileName);

	List<File>  findAllByFileNameAndDateOfUploadAndField(String FileName,Date dateOfUpload,String field);
	List<File>  findAllByDateOfUpload(Date dateOfUpload);
	List<File>  findAllByField(String field);
	List<File> findByStartupper(Optional<Startupper> startupperId);



}
