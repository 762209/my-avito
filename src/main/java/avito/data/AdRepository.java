package avito.data;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import avito.domain.Ad;


public interface AdRepository extends PagingAndSortingRepository<Ad, Long>,
JpaSpecificationExecutor<Ad>{
}
