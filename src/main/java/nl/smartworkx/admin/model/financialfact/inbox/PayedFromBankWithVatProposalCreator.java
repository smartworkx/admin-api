package nl.smartworkx.admin.model.financialfact.inbox;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.TaxCalculator.HIGH;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
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
        final Amount amountVatIncluded = financialFact.getAmount();
        final Amount amountVat = amountVatIncluded.calculateIncVat(vatTaxRate());
        final Amount amountExVat = amountVatIncluded.subtract(amountVat);
        return builder(ledgerRepository)
                .add(costLedger(), DEBIT, amountExVat)
                .add("DVAT", DEBIT, amountVat)
                .add("BANK", CREDIT, amountVatIncluded)
                .build();
    }

    protected double vatTaxRate() {
        return HIGH;
    }

    protected abstract String descriptionContains();

    protected abstract String costLedger();
}
