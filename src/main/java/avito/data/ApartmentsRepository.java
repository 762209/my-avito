package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.Apartments;

public interface ApartmentsRepository extends CrudRepository<Apartments, Long>{

}
