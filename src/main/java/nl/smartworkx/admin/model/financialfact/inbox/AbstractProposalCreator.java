package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
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
    public List<RecordFormLine> create(FinancialFact financialFact) {
        List<RecordFormLine> records = onCreate(financialFact);
        log.info("{} proposing {}", this.getClass().getSimpleName(), records);
        return records;
    }

    boolean descriptionContainsAny(FinancialFact financialFact, String... stringsToContain) {
        return Stream.of(stringsToContain).anyMatch(s -> financialFact.getDescription().toLowerCase().contains(s.toLowerCase()));
    }

    protected abstract List<RecordFormLine> onCreate(FinancialFact financialFact);
}
