package avito.forms;

import static avito.domain.Ad.AdCategory.HOUSES;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import avito.domain.Ad;
import avito.domain.House;
import avito.domain.Photo;
import avito.domain.RealEstate;
import lombok.Data;

@Data
public class HouseForm {
	
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
	
	private List<Photo> photos = new ArrayList<Photo>();
	
	@Digits(integer=13, fraction = 2,  message="Поле 'Площадь' должно быть числом")
	private int floorArea;
	
	@Digits(integer=13, fraction = 2,  message="Поле 'Площадь участка' должно быть числом")
	private int landArea;
	@Digits(integer=13, fraction = 2,  message="Поле 'Колличество этажей' должно быть числом")
	private int floorLevels;
	
	public Ad toAd() {
		Ad ad = new Ad(name, description, price, city, adress, HOUSES);
		RealEstate realEstate = new RealEstate(floorArea);
		House house = new House(landArea, floorLevels);
		realEstate.setHouseAd(house);
		ad.setRealEstateAd(realEstate);
		
		return ad;
	}
	
	public Ad update() {
		Ad ad = new Ad(name, description, price, city, adress, HOUSES);
		RealEstate realEstate = new RealEstate(floorArea);
		House house = new House(landArea, floorLevels);
		realEstate.setHouseAd(house);;
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
		this.landArea = ad.getRealEstateAd().getHouseAd().getLandArea();
		this.floorLevels = ad.getRealEstateAd().getHouseAd().getFloorLevels();
	}
}
