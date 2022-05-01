package design.boilerplate.springboot.security.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import design.boilerplate.springboot.model.Startupper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

/**
 * Created on Ağustos, 2020
 *
 * @author taha
 */
@Getter
@Setter
@NoArgsConstructor
public class FileRequest {

	@NotEmpty(message = "{fileName_not_empty}")
	private String fileName;

	@NotEmpty(message = "{dateOfUpload_not_empty}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	private Date dateOfUpload;

	@NotEmpty(message = "{field_not_empty}")
	private String field;


}



