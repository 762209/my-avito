package avito.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Apartments.class)
public class Apartments_ {
	public static volatile SingularAttribute<Ad, Integer> roomsNumber;
	public static volatile SingularAttribute<Ad, Integer> floorLevel;
}
