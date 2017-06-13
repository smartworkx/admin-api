package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.TaxRate;
import nl.smartworkx.admin.model.ledger.LedgerCode;

/**
 *
 */
@Component
public class IncomingInvoiceProposalCreator extends AbstractProposalCreator {

    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getOrigin() != null && financialFact.getOrigin().getType().equals("INCOMING_INVOICE");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amountVatIncluded = financialFact.getAmount();
        final Amount amountVat = amountVatIncluded.calculateVatForAmountWithVatIncluded(getVatTaxRate());
        final Amount amountExVat = amountVatIncluded.subtract(amountVat);
        return builder(ledgerRepository)
                .add(counterLedger(), DEBIT, amountExVat)
                .add(LedgerCode.DVAT, DEBIT, amountVat)
                .add(LedgerCode.CRED, CREDIT, amountVatIncluded)
                .build();
    }

    private TaxRate getVatTaxRate() {
        return TaxRate.HIGH;
    }

    private String counterLedger() {
            return null;
    }

}
