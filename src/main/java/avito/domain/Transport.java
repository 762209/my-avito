package avito.domain;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Transport implements Serializable {
	
	private static final long serialVersionUID = 3344904810182624983L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String model;
	private String brand;
	private String color;
	private String manufactureYear;
	private int mileage;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private Car carAd;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private TruckSpecMach truckSpecMachAd;
	
	public Transport() {}
	
	public Transport(String model, String brand, String color, String manufactureYear, int mileage) {
		this.model = model;
		this.brand = brand;
		this.color = color;
		this.manufactureYear = manufactureYear;
		this.mileage = mileage;
	}
}
