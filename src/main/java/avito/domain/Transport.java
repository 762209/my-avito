package avito.domain;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3344904810182624983L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String model;
	private String brand;
	private String color;
	private String manufactureYear;
	private int mileage;
	@Column(length = 1024)
	private Car carAd;
	@Column(length = 1024)
	private TruckSpecMach truckSpecMachAd;
}
