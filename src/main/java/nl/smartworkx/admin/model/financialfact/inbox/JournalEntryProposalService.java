package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
@Service
public class JournalEntryProposalService {

    private final Set<ProposalCreator> proposalCreators;

    public JournalEntryProposalService(Set<ProposalCreator> proposalCreators) {
        this.proposalCreators = proposalCreators;
    }


    public List<Record> createProposedRecords(FinancialFact financialFact) {
        return this.proposalCreators.stream()
                .filter(proposalCreator -> proposalCreator.matches(financialFact))
                .findFirst()
                .map(proposalCreator -> proposalCreator
                        .create(financialFact))
                .orElse(Collections.emptyList());
    }
}
