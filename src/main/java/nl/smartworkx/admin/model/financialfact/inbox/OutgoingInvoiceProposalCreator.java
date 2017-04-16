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
public class OutgoingInvoiceProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getOrigin().getType().equals("OUTGOING_INVOICE");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amountExVat = financialFact.getAmount();
        final Amount amountVat = amountExVat.calculateExVat(HIGH);
        final Amount amountVatIncluded = amountExVat.add(amountVat);
        return builder(ledgerRepository)
                .add("DEB", DEBIT, amountVatIncluded)
                .add("TOJ", CREDIT, amountExVat)
                .add("VATS", CREDIT, amountVat)
                .build();
    }

}
