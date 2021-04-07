package avito.data;

import org.springframework.data.repository.CrudRepository;

import avito.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);
	boolean existsByUsername(String username);
}
