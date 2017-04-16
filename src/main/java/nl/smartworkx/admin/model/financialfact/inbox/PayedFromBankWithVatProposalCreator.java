package nl.smartworkx.admin.model.financialfact.inbox;

import static nl.smartworkx.admin.model.TaxCalculator.HIGH;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 *
 */
public abstract class PayedFromBankWithVatProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getDescription().toLowerCase().contains(descriptionContains());
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        return ProposalUtils.createRecordsFromBankWithVat(ledgerRepository, financialFact.getAmount(), vatTaxRate(), costLedger());
    }

    protected double vatTaxRate() {
        return HIGH;
    }

    protected abstract String descriptionContains();

    protected abstract String costLedger();
}
