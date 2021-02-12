package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Long>{

}
