package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
public interface ProposalCreator {
    boolean matches(FinancialFact financialFact);

    List<RecordFormLine> create(FinancialFact financialFact);
}
