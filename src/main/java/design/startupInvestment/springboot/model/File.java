
package design.startupInvestment.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;


@Entity
@NoArgsConstructor
@Getter
@Setter
	@Table(name = "FILES")
	public class File {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

	private String fileName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	private Date dateOfUpload;

	private String field;

	private String fileDownloadUri;

	private String fileType;

	private long size;



	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "startupper_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Startupper startupper;

	public File(String fileName, Date dateOfUpload, String field) {
		this.fileName = fileName;
		this.dateOfUpload = dateOfUpload;
		this.field = field;
	}

}
