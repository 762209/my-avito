package avito.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EducationEstablishment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2070432922981640341L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private LocalDate graduatingDate;
}
