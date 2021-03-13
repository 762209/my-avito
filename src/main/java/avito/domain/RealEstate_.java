package avito.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import avito.domain.RealEstate.RealEstateCategory;

@StaticMetamodel(RealEstate.class)
public class RealEstate_ {
	public static volatile SingularAttribute<Ad, Integer> floorArea;
	public static volatile SingularAttribute<Ad, Apartments> apartmentsAd;
	public static volatile SingularAttribute<Ad, House> houseAd;
	public static volatile SingularAttribute<Ad, RealEstateCategory> realEstateCategory;
}
