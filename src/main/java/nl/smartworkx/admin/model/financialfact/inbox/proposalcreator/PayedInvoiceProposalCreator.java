package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.inbox.proposalcreator.AbstractProposalCreator;

/**
 *
 */
@Component
public class PayedInvoiceProposalCreator extends AbstractProposalCreator {

    @Override
    public boolean matches(FinancialFact financialFact) {
        return descriptionContainsAny(financialFact, "first eight", "fixedtoday", "fixed today", "DELTA R", "BRAINNET BV","MAXEDA DIY");
    }

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amount = financialFact.getAmount();
        return builder(ledgerRepository)
                .add("BANK", DEBIT, amount)
                .add("DEB", CREDIT, amount)
                .build();
    }
}
