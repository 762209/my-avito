package avito.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class House implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 657631126283883295L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int landArea;
	private int floorLevels;
}
