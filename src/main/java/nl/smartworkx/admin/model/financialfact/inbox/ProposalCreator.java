package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.List;

import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
public interface ProposalCreator {
    boolean matches(FinancialFact financialFact);

    List<Record> create(FinancialFact financialFact);
}
