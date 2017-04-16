package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.LedgerRepository;

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
                .orElse(Collections.emptyList());
    }

    public List<RecordFormLine> createProposedRecords(JournalEntryProposalParameters parameters) {
        final Amount amount = new Amount(parameters.getAmount());
        if (parameters.getType().equals(ProposalType.COSTS)) {
            if (parameters.getTaxRate() != null) {
                return ProposalUtils.createRecordsFromBankWithVat(ledgerRepository, amount, parameters.getTaxRate(), "COSTS");
            } else {
                return ProposalUtils.createRecordsFromBank(ledgerRepository, amount,"COSTS");
            }
        } else if (parameters.getType() == ProposalType.PRIVATE){
            return ProposalUtils.createRecordsFromBank(ledgerRepository, amount, "PRIVJ");
        } else {
            return Collections.emptyList();
        }
    }
}
