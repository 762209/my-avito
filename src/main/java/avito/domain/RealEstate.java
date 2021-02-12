package avito.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class RealEstate implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3963987240203882091L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int floorArea;
	private Apartments apartmentsAd;
	private House houseAd;
	@Enumerated(EnumType.STRING)
	private RealEstateCategory realEstateCategory;
	
	public enum RealEstateCategory {
		APARTMENTS, HOUSES, GARAGE;
	}
}
