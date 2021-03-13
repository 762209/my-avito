package avito.domain;

import java.time.LocalDateTime;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import avito.domain.Ad.AdCategory;

@StaticMetamodel(Ad.class)
public class Ad_ {
	public static volatile SingularAttribute<Ad, Long> id; 
	public static volatile SingularAttribute<Ad, String> name;
	public static volatile SingularAttribute<Ad, String> description;
	public static volatile SingularAttribute<Ad, String> city;
	public static volatile SingularAttribute<Ad, Long> price;
	public static volatile SingularAttribute<Ad, LocalDateTime> createdAt;
	public static volatile ListAttribute<Ad, Photo> photos;
	public static volatile SingularAttribute<Ad, Transport> transportAd;
	public static volatile SingularAttribute<Ad, RealEstate> realEstateAd;
	public static volatile SingularAttribute<Ad, AdCategory> adCategory; 
}
