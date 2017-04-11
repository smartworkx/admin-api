package nl.smartworkx.admin.application.financialfact.inbox;

import nl.smartworkx.admin.model.FinancialFact;

/**
 *
 */
public class InboxFinancialFact {
    private FinancialFact financialFact;

    public InboxFinancialFact(FinancialFact financialFact) {

        this.financialFact = financialFact;
    }

    public FinancialFact getFinancialFact() {
        return financialFact;
    }
}
