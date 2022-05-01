
package design.boilerplate.springboot.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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

		@ManyToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(name = "startupper_id", nullable = false)
		@OnDelete(action = OnDeleteAction.CASCADE)
		@JsonIgnore
		private Startupper startupper;

	public File(String fileName, Date dateOfUpload, String field) {
		this.fileName = fileName;
		this.dateOfUpload = dateOfUpload;
		this.field = field;
	}

}
