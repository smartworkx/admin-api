package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 * Created by joris on 2-6-17.
 */
@Component
public class ZOtherCostsProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return DebitCredit.DEBIT.equals(financialFact.getDebitCredit());
    }

    @Override
    protected List<RecordFormLine> onCreate(FinancialFact financialFact) {
        return builder(ledgerRepository)
                .add("CRED", DEBIT, financialFact.getAmount())
                .add("BANK", CREDIT, financialFact.getAmount())
                .build();
    }
}
