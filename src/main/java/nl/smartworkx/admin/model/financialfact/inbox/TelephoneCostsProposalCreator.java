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
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
@Component
public class TelephoneCostsProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getDescription().toLowerCase().contains("simyo");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amountVatIncluded = financialFact.getAmount();
        final Amount amountVat = amountVatIncluded.calculateIncVat(HIGH);
        final Amount amountExVat = amountVatIncluded.subtract(amountVat);
        return builder(ledgerRepository)
                .add("TELC", DEBIT, amountExVat)
                .add("DVAT", DEBIT, amountVat)
                .add("BANK", CREDIT, amountVatIncluded)
                .build();
    }

}
