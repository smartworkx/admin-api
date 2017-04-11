package nl.smartworkx.admin.application.financialfact.inbox;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;

/**
 *
 */
@Service
public class InboxFinancialFactService {

    private final FinancialFactRepository financialFactRepository;

    public InboxFinancialFactService(FinancialFactRepository financialFactRepository) {
        this.financialFactRepository = financialFactRepository;
    }

    public List<InboxFinancialFact> getInboxFinancialFacts() {
        final List<InboxFinancialFact> inboxItems = new ArrayList<>();
        financialFactRepository.findNonJournalizedFinancialFacts().forEach(financialFact -> {
            inboxItems.add(new InboxFinancialFact(financialFact));
        });
        return inboxItems;
    }
}
