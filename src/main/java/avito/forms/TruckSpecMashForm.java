package avito.forms;

import static avito.domain.Ad.AdCategory.TRUCK_AND_SPECIAL_MACHINERY;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import avito.domain.Ad;
import avito.domain.Transport;
import avito.domain.TruckSpecMach;
import lombok.Data;

@Data
public class TruckSpecMashForm {
	
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
	
	@Digits(integer=13, fraction = 2,  message="Поле 'Наработка' должно быть числом")
	private int operatingTime;
	
	public Ad toAd() {
		Ad ad = new Ad(name, description, price, city, adress, TRUCK_AND_SPECIAL_MACHINERY);
		Transport transport = new Transport(model, brand, color, manufactureYear, mileage);
		TruckSpecMach truckSpecMach = new TruckSpecMach(operatingTime);
		transport.setTruckSpecMachAd(truckSpecMach);
		ad.setTransportAd(transport);
		
		return ad;
	}
	public Ad update() {
		Ad ad = new Ad(name, description, price, city, adress, TRUCK_AND_SPECIAL_MACHINERY);
		Transport transport = new Transport(model, brand, color, manufactureYear, mileage);
		TruckSpecMach truckSpecMach = new TruckSpecMach(operatingTime);
		transport.setTruckSpecMachAd(truckSpecMach);
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
		
		this.operatingTime = ad.getTransportAd().getTruckSpecMachAd().getOperatingTime();
	}
}
