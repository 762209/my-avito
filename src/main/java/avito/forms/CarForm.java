package avito.forms;

import static avito.domain.Ad.AdCategory.CAR;


import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import avito.domain.Ad;
import avito.domain.Car;
import avito.domain.Transport;
import lombok.Data;

@Data
public class CarForm {
	
	private long id;
	
	@NotBlank(message="Поле 'Имя' не может быть пустым")
	private String name;
	@Column(length = 500)
	@NotBlank(message="Поле 'Описание' не может быть пустым")
	private String description;
	@Digits(integer=13, fraction = 2,  message="Поле 'Цена' должно быть числом")
	private long price;
	@NotBlank(message="Поле 'Город' не может быть пустым")
	private String city;
	@NotBlank(message="Поле 'Адрес' не может быть пустым")
	private String adress;
	
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
	
	@NotBlank(message="Поле 'Коробка передач' не может быть пустым")
	private String transmission;
	@NotBlank(message="Поле 'Привод' не может быть пустым")
	private String driveType;
	@NotBlank(message="Поле 'Тип двигателя' не может быть пустым")
	private String enginesType;
	
	public Ad toAd() {
		Ad ad = new Ad(name, description, price, city, adress, CAR);
		Transport transport = new Transport(model, brand, color, manufactureYear, mileage);
		Car car = new Car(transmission, driveType, enginesType);
		transport.setCarAd(car);
		ad.setTransportAd(transport);
		return ad;
	}
	public Ad update() {
		Ad ad = new Ad(name, description, price, city, adress, CAR);
		Transport transport = new Transport(model, brand, color, manufactureYear, mileage);
		Car car = new Car(transmission, driveType, enginesType);
		transport.setCarAd(car);
		ad.setTransportAd(transport);
		ad.setId(id);
		return ad;
	}
	public void loadData(Ad ad) {
		this.id = ad.getId();
		this.name = ad.getName();
		this.description = ad.getDescription();
		this.price = ad.getPrice();
		this.city = ad.getCity();
		this.adress = ad.getAdress();
		
		this.model = ad.getTransportAd().getModel();
		this.brand = ad.getTransportAd().getBrand();
		this.color = ad.getTransportAd().getColor();
		this.manufactureYear = ad.getTransportAd().getManufactureYear();
		this.mileage = ad.getTransportAd().getMileage();
		
		this.transmission = ad.getTransportAd().getCarAd().getTransmission();
		this.driveType = ad.getTransportAd().getCarAd().getDriveType();
		this.enginesType = ad.getTransportAd().getCarAd().getEnginesType();
	}
}
