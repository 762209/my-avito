package avito.specifications;

import org.springframework.data.jpa.domain.Specification;

import avito.domain.Ad;
import avito.domain.Ad_;

public class AdSpecification{

	public static Specification<Ad> hasName(Ad criteria) {
		if (criteria.getName() == null || criteria.getName() == "") {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Ad_.name), criteria.getName());
		});
	}
	
	public static Specification<Ad> hasCity(Ad criteria) {
		if (criteria.getCity() == null || criteria.getCity() == "") {
			return null;
		}
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get(Ad_.city), criteria.getCity());
		});
	}
}
