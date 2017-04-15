package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Ledger;
import nl.smartworkx.admin.model.journal.LedgerRepository;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
@Component
@Slf4j
public abstract class AbstractProposalCreator implements ProposalCreator {

    @Autowired
    protected LedgerRepository ledgerRepository;

    protected Ledger ledger(String ledgerCode) {
        return ledgerRepository.findByCode(ledgerCode);
    }

    @Override
    public List<Record> create(FinancialFact financialFact) {
        List<Record> records = onCreate(financialFact);
        log.info("{} proposing {}", this.getClass().getSimpleName(), records);
        return records;
    }

    protected abstract List<Record> onCreate(FinancialFact financialFact);
}
