package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.TaxCalculator.HIGH;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.TaxRate;
import nl.smartworkx.admin.model.financialfact.inbox.proposalcreator.AbstractProposalCreator;

/**
 *
 */
@Component
public class OutgoingInvoiceProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getOrigin() != null && financialFact.getOrigin().getType().equals("OUTGOING_INVOICE");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amountVatInc = financialFact.getAmount();
        final Amount amountVat = amountVatInc.calculateVatForAmountWithVatIncluded(TaxRate.HIGH);
        final Amount amountExVat = amountVatInc.subtract(amountVat);
        return builder(ledgerRepository)
                .add("DEB", DEBIT, amountVatInc)
                .add("TOJ", CREDIT, amountExVat)
                .add("VATS", CREDIT, amountVat)
                .build();
    }

}
