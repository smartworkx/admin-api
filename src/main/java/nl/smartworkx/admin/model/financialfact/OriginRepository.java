package nl.smartworkx.admin.model.financialfact;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import nl.smartworkx.admin.model.financialfact.Origin;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "origins", path = "origins")
public interface OriginRepository extends CrudRepository<Origin, Long> {
}
