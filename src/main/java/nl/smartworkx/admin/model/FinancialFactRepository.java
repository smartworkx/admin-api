package nl.smartworkx.admin.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "financial-facts", path = "financial-facts")
public interface FinancialFactRepository extends CrudRepository<FinancialFact, Long> {
}
