package nl.smartworkx.admin.model.ledger;

import java.util.List;
import javax.persistence.EntityManager;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class LedgerRepositoryImpl extends SimpleJpaRepository<Ledger,Long> implements CustomLedgerRepository {

    public LedgerRepositoryImpl(EntityManager em) {
        super(Ledger.class, em);
    }


    @Override
    public List<Ledger> findAll() {
        return getQuery(null, new Sort("code")).getResultList();
    }
}
