package avito.forms;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import avito.domain.Ad;
import lombok.Data;

@Data
public class AdForm {
	@NotBlank(message="Поле 'Имя' не может быть пустым")
	private String name;
	@NotBlank(message="Поле 'Описание' не может быть пустым")
	private String description;
	@Digits(integer=13, fraction = 2,  message="Поле 'Цена' должно быть числом")
	private long price;
	@NotBlank(message="Поле 'Город' не может быть пустым")
	private String city;
	@NotBlank(message="Поле 'Адрес' не может быть пустым")
	private String adress;
	
	public Ad toAd() {
		Ad ad = new Ad(name, description, price, city, adress);
		return ad;
	}
}
