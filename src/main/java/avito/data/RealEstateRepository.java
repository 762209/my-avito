package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.RealEstate;

public interface RealEstateRepository extends CrudRepository<RealEstate, Long>{

}
