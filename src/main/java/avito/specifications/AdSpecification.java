package avito.specifications;

import org.springframework.data.jpa.domain.Specification;

import avito.domain.Ad;
import avito.domain.Ad_;
import avito.domain.User;
import avito.domain.User_;

public class AdSpecification{

	public static Specification<Ad> hasName(Ad criteria) {
		if (criteria.getName() == null || criteria.getName() == "") {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.like(criteriaBuilder.lower(root.get(Ad_.name)), "%"+criteria.getName().toLowerCase()+"%");
		});
	}
	
	public static Specification<Ad> hasCity(Ad criteria) {
		if (criteria.getCity() == null || criteria.getCity() == "") {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(criteriaBuilder.lower(root.get(Ad_.city)), criteria.getCity().toLowerCase());
		});
	}
	
	public static Specification<Ad> hasCategory(Ad criteria) {
		if (criteria.getAdCategory() == null) {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Ad_.adCategory), criteria.getAdCategory());
		});
	}
	
	public static Specification<Ad> hasUser(User user) {
		if (user == null) {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Ad_.user).get(User_.username), user.getUsername());
		});
	}
}
