package nl.smartworkx.admin.model.balance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

/**
 * Created by joris on 24-4-17.
 */
@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "balances", path = "balances")
public interface BalanceRepository extends CrudRepository<Balance, Long> {
    @Query("select b from Balance b join fetch b.accounts where b.date < ?1")
    Balance findPreviousBalance(LocalDate date);
}
