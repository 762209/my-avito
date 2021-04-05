package avito.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Car.class)
public class Car_ {
	public static volatile SingularAttribute<Ad, String> transmission;
	public static volatile SingularAttribute<Ad, String> driveType;
	public static volatile SingularAttribute<Ad, String> enginesType;
}
