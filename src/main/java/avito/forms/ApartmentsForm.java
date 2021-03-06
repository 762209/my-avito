package avito.forms;

import static avito.domain.Ad.AdCategory.APARTMENTS;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import avito.domain.Ad;
import avito.domain.Apartments;
import avito.domain.RealEstate;
import lombok.Data;

@Data
public class ApartmentsForm {
	
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
	
	@Digits(integer=13, fraction = 2,  message="Поле 'Площадь' должно быть числом")
	private int floorArea;
	
	@Digits(integer=13, fraction = 2,  message="Поле 'Кол' должно быть числом")
	private int roomsNumber;
	@Digits(integer=13, fraction = 2,  message="Поле 'Площадь' должно быть числом")
	private int floorLevel;
	
	public Ad toAd() {
		Ad ad = new Ad(name, description, price, city, adress, APARTMENTS);
		RealEstate realEstate = new RealEstate(floorArea);
		Apartments apartments = new Apartments(roomsNumber, floorLevel);
		realEstate.setApartmentsAd(apartments);
		ad.setRealEstateAd(realEstate);
		return ad;
	}
	public Ad update() {
		Ad ad = new Ad(name, description, price, city, adress, APARTMENTS);
		RealEstate realEstate = new RealEstate(floorArea);
		Apartments apartments = new Apartments(roomsNumber, floorLevel);
		realEstate.setApartmentsAd(apartments);
		ad.setRealEstateAd(realEstate);
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
		
		this.floorArea = ad.getRealEstateAd().getFloorArea();
		
		this.roomsNumber = ad.getRealEstateAd().getApartmentsAd().getRoomsNumber();
		this.floorLevel = ad.getRealEstateAd().getApartmentsAd().getFloorLevel();
	}
}
