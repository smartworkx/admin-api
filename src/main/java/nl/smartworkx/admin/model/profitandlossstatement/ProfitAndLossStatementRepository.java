package nl.smartworkx.admin.model.profitandlossstatement;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import nl.smartworkx.admin.model.balance.Balance;

/**
 * Created by joris on 24-4-17.
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "profit-and-loss-statements", path = "profit-and-loss-statements")
public interface ProfitAndLossStatementRepository extends CrudRepository<ProfitAndLossStatement, Long> {
}
