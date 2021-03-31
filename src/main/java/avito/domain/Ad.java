package avito.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Ad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
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
	private LocalDateTime createdAt;
	
	@OneToMany(targetEntity = Photo.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Photo> photos = new ArrayList<Photo>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private Transport transportAd;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private RealEstate realEstateAd;
	@Enumerated(EnumType.STRING)
	private AdCategory adCategory;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn()
	private User user;

	public Ad() {}
	
	public Ad(String name, String description, long price,
			String city, String adress, AdCategory category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.city = city;
		this.adress = adress;
		this.adCategory = category;
	}
	
	@PrePersist
	void createdAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public enum AdCategory {
		CAR, MOTORCYCLE, TRUCK_AND_SPECIAL_MACHINERY, APARTMENTS, HOUSES, GARAGE, SERVICE, ANIMAL;
	}
}
