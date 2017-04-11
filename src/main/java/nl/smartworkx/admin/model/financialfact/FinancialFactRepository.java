package nl.smartworkx.admin.model.financialfact;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
    @Query("select ff from FinancialFact ff where not exists (select je from JournalEntry je where je.financialFactId = ff.id)")
    List<FinancialFact> findNonJournalizedFinancialFacts();
}
