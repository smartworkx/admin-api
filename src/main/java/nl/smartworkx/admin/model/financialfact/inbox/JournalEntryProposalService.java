package nl.smartworkx.admin.model.financialfact.inbox;

import static java.util.Collections.emptyList;
import static nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils.createRecordsFromBank;
import static nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils.createRecordsToCred;
import static nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils.createRecordsToCredWithVat;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.inbox.proposalcreator.ProposalCreator;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

/**
 *
 */
@Service
public class JournalEntryProposalService {

    private final Set<ProposalCreator> proposalCreators;
    private final LedgerRepository ledgerRepository;

    public JournalEntryProposalService(Set<ProposalCreator> proposalCreators, LedgerRepository ledgerRepository) {
        this.proposalCreators = proposalCreators;
        this.ledgerRepository = ledgerRepository;
    }


    public List<RecordFormLine> createProposedRecords(FinancialFact financialFact) {
        return this.proposalCreators.stream()
                .filter(proposalCreator -> proposalCreator.matches(financialFact))
                .findFirst()
                .map(proposalCreator -> proposalCreator
                        .create(financialFact))
                .orElse(emptyList());
    }

    public List<RecordFormLine> createProposedRecords(JournalEntryProposalParameters parameters) {
        final Amount amount = new Amount(parameters.getAmount());
        if (parameters.getType().equals(ProposalType.COSTS)) {
            if (parameters.getTaxRate() != null) {
                return createRecordsToCredWithVat(ledgerRepository, amount, parameters.getTaxRate(), null);
            } else {
                return createRecordsToCred(ledgerRepository, amount, null);
            }
        } else if (parameters.getType() == ProposalType.PRIVATE) {
            return createRecordsFromBank(ledgerRepository, amount, "PRIVJ");
        } else if (parameters.getType() == ProposalType.CREDIT) {
            return createRecordsFromBank(ledgerRepository, amount, "CRED");
        } else {
            return emptyList();
        }
    }
}
