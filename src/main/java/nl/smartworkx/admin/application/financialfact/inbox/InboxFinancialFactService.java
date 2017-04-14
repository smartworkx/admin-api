package nl.smartworkx.admin.application.financialfact.inbox;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalService;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
@Service
public class InboxFinancialFactService {

    private final FinancialFactRepository financialFactRepository;

    private final JournalEntryProposalService journalEntryProposalService;

    public InboxFinancialFactService(FinancialFactRepository financialFactRepository, JournalEntryProposalService journalEntryProposalService) {
        this.financialFactRepository = financialFactRepository;
        this.journalEntryProposalService = journalEntryProposalService;
    }

    public List<InboxFinancialFact> getInboxFinancialFacts() {
        final List<InboxFinancialFact> inboxItems = new ArrayList<>();
        financialFactRepository.findNonJournalizedFinancialFacts().forEach(financialFact -> {
            inboxItems.add(createProposal(financialFact));
        });
        return inboxItems;
    }

    private InboxFinancialFact createProposal(FinancialFact financialFact) {
        List<Record> records = journalEntryProposalService.createProposedRecords(financialFact);
        return new InboxFinancialFact(financialFact, records);
    }
}
