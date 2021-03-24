package avito.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import lombok.Data;

@Data
@Entity
public class Ad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String description;
	private long price;
	private String city;
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
