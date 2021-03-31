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
	
	@NotBlank(message="Поле 'Модель' не может быть пустым")
	private String model;
	@NotBlank(message="Поле 'Бренд' не может быть пустым")
	private String brand;
	@NotBlank(message="Поле 'Цвет' не может быть пустым")
	private String color;
	@NotBlank(message="Поле 'Год выпуска' не может быть пустым")
	private String manufactureYear;
	@Digits(integer=13, fraction = 2,  message="Поле 'Пробег' должно быть числом")
	private int mileage;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private Car carAd;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private TruckSpecMach truckSpecMachAd;
}
