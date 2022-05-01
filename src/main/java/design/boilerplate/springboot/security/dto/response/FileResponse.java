package design.boilerplate.springboot.security.dto.response;

import design.boilerplate.springboot.model.Startup;
import design.boilerplate.springboot.model.Startupper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {

	private Long id;

	private String fileName;

	private Date dateOfUpload;

	private String field;

	private StartupperResponse startupper;


}
