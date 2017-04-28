package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.TaxRate;
import nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils;
import nl.smartworkx.admin.model.financialfact.inbox.proposalcreator.AbstractProposalCreator;

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
        return ProposalUtils.createRecordsFromBankWithVat(ledgerRepository, financialFact.getAmount(), vatTaxRate(), counterLedger());
    }

    protected TaxRate vatTaxRate() {
        return TaxRate.HIGH;
    }

    protected abstract String descriptionContains();

    protected abstract String counterLedger();
}
