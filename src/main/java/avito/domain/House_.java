package avito.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(House.class)
public class House_ {
	public static volatile SingularAttribute<Ad, Integer> landArea; 
	public static volatile SingularAttribute<Ad, Integer> floorLevels; 
}
