package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long>{

}
