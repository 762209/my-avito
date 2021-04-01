package avito.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Car implements Serializable{
	
	private static final long serialVersionUID = -7110344880084091095L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String transmission;
	private String driveType;
	private String enginesType;
	
	public Car() {}
	
	public Car(String transmission, String driveType, String enginesType) {
		this.transmission = transmission;
		this.driveType = driveType;
		this.enginesType = enginesType;
	}
}
