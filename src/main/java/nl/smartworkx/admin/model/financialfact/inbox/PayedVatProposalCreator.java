package nl.smartworkx.admin.model.financialfact.inbox;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.TaxCalculator.HIGH;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 *
 */
@Component
public class PayedVatProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return descriptionContainsAny(financialFact,"belastingdienst");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amount = financialFact.getAmount();
        return builder(ledgerRepository)
                .add("BANK", CREDIT, amount)
                .add("VATS", DEBIT, amount)
                .build();
    }

}
