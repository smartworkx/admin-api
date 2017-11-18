package nl.smartworkx.admin.model.journal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "journal-entries", path = "journal-entries")
public interface JournalEntryRepository extends PagingAndSortingRepository<JournalEntry, Long>, JpaSpecificationExecutor<JournalEntry>,
        JournalEntryRepositoryCustom {
}