package nl.smartworkx.admin.application.financialfact.inbox;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
public class InboxFinancialFact {
    private FinancialFact financialFact;
    private List<RecordFormLine> records;

    public InboxFinancialFact(FinancialFact financialFact, List<RecordFormLine> records) {
        this.financialFact = financialFact;
        this.records = records;
    }

    public List<RecordFormLine> getRecords() {
        return records;
    }

    public FinancialFact getFinancialFact() {
        return financialFact;
    }
}
