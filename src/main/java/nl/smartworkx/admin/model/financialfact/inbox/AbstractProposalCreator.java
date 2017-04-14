package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.journal.Ledger;
import nl.smartworkx.admin.model.journal.LedgerRepository;

/**
 *
 */
@Component
public abstract class AbstractProposalCreator implements ProposalCreator{

    @Autowired
    protected LedgerRepository ledgerRepository;

    protected Ledger ledger(String ledgerCode) {
        return ledgerRepository.findByCode(ledgerCode);
    }
}
