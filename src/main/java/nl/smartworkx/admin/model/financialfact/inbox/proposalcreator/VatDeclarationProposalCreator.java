package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder;
import nl.smartworkx.admin.model.ledger.LedgerCode;

/**
 * Created by joris on 12-6-17.
 */
@Component
public class VatDeclarationProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getOrigin() != null && financialFact.getOrigin().getType().equals("VAT_DECLARATION");
    }

    @Override
    protected List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amount = financialFact.getAmount();
        return RecordsBuilder.builder(ledgerRepository)
                .add(LedgerCode.VATS, DEBIT, amount)
                .add(LedgerCode.VATP, CREDIT, amount)
                .build();
    }
}
