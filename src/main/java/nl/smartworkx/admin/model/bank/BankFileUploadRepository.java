package nl.smartworkx.admin.model.bank;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RepositoryRestResource(collectionResourceRel = "bank-file-uploads", path = "bank-file-uploads")
public interface BankFileUploadRepository extends CrudRepository<BankFileUpload, Long> {
}
