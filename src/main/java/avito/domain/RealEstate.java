package avito.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class RealEstate implements Serializable{
	
	private static final long serialVersionUID = 3963987240203882091L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int floorArea;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private Apartments apartmentsAd;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn
	private House houseAd;
	
	public RealEstate() {}
	public RealEstate(int floorArea) {
		this.floorArea = floorArea;
	}
}
