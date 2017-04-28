package nl.smartworkx.admin.adapters.tax.vat.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "vat-declarations", path = "vat-declarations")
public interface VatDeclarationRepository extends PagingAndSortingRepository<VatDeclaration, Long> {
}
