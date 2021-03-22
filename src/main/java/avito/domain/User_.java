package avito.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> username; 
	public static volatile SingularAttribute<User, String> password; 
	public static volatile SingularAttribute<User, String> fullname; 
	public static volatile SingularAttribute<User, String> street; 
	public static volatile SingularAttribute<User, String> city; 
	public static volatile SingularAttribute<User, String> zip; 
	public static volatile SingularAttribute<User, String> phoneNumber;
	public static volatile ListAttribute<User, Ad> ads;
}
