package avito.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Transport.class)
public class Transport_ {
	public static volatile SingularAttribute<Ad, String> model;
	public static volatile SingularAttribute<Ad, String> brand;
	public static volatile SingularAttribute<Ad, String> color;
	public static volatile SingularAttribute<Ad, String> manufactureYear;
	public static volatile SingularAttribute<Ad, Integer> mileage;
	public static volatile SingularAttribute<Ad, Car> carAd;
	public static volatile SingularAttribute<Ad, TruckSpecMach> truckSpecMachAd;
}

