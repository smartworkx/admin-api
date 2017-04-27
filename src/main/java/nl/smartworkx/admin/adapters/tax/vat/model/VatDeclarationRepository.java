package nl.smartworkx.admin.adapters.tax.vat.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import nl.smartworkx.admin.adapters.ing.BankFileUpload;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RepositoryRestResource(collectionResourceRel = "vat-declarations", path = "vat-declarations")
public interface VatDeclarationRepository extends CrudRepository<VatDeclaration, Long> {
}
