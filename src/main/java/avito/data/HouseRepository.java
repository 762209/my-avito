package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.House;

public interface HouseRepository extends CrudRepository<House, Long>{

}
