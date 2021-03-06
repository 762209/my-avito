package avito.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TruckSpecMach implements Serializable {
	
	private static final long serialVersionUID = 3800012000378020534L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int operatingTime;

	public TruckSpecMach() {}

	public TruckSpecMach(int operatingTime) {
		this.operatingTime = operatingTime;
	}
}
