package avito.domain;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Apartments implements Serializable{
	
	private static final long serialVersionUID = 6687916892245688531L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int roomsNumber;
	private int floorLevel;
	
	public Apartments() {}
	
	public Apartments(int roomsNumber, int floorLevel) {
		this.roomsNumber = roomsNumber;
		this.floorLevel = floorLevel;
	}
}
