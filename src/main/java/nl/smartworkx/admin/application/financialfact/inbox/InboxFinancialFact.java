package nl.smartworkx.admin.application.financialfact.inbox;

import java.util.List;

import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
public class InboxFinancialFact {
    private FinancialFact financialFact;
    private List<Record> records;

    public InboxFinancialFact(FinancialFact financialFact, List<Record> records) {
        this.financialFact = financialFact;
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }
}
