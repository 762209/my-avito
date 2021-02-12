package avito.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@OneToMany(targetEntity = Photo.class)
	private List<Photo> photos = new ArrayList<Photo>();
	@Column(length = 1024)
	private Transport transportAd;
	@Column(length = 1024)
	private RealEstate realEstateAd;
	
	@Enumerated(EnumType.STRING)
	private AdCategory adCategory;
	
	@PrePersist
	void createdAt() {
		this.createdAt = LocalDateTime.now();
	}
	
	public enum AdCategory {
		TRANSPORT, REAL_ESTATE, SERVICE, ANIMAL;
	}
}
