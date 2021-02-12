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
public class Workplace implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1169766502994303564L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String companyName;
	private String itn; //individual taxpayer number
	private LocalDate fromDate;
	private LocalDate toDate;
}
