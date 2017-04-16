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
public abstract class PayedFromBankWithoutVatProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getDescription().toLowerCase().contains(descriptionContains());
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amount = financialFact.getAmount();
        return builder(ledgerRepository)
                .add(costLedger(), DEBIT, amount)
                .add("BANK", CREDIT, amount)
                .build();
    }


    protected abstract String descriptionContains();

    protected abstract String costLedger();
}
