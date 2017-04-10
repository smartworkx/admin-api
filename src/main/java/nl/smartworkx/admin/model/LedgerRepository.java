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
@RepositoryRestResource(collectionResourceRel = "ledgers", path = "ledgers")
public interface LedgerRepository extends CrudRepository<Ledger, Long> {

	/**
	 * Finds by code
	 *
	 * @param code to find the Ledger
	 * @return the ledger for the code
	 */
	Ledger findByCode(String code);
}
