package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.DebitCredit.CREDIT;
import static nl.smartworkx.admin.model.DebitCredit.DEBIT;
import static nl.smartworkx.admin.model.financialfact.inbox.RecordsBuilder.builder;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.TaxRate;

/**
 *
 */
@Component
public class TempTracProposalCreator extends AbstractProposalCreator {


    @Override
    public boolean matches(FinancialFact financialFact) {
        return descriptionContainsAny(financialFact, " OV-Chipkaart","NS GROEP","Ryw Shop Stat Nym");
    }

    @Override
    protected List<RecordFormLine> onCreate(FinancialFact financialFact) {
        return builder(ledgerRepository)
                .add("TRAC", DEBIT, financialFact.getAmount())
                .add("BANK", CREDIT, financialFact.getAmount())
                .build();
    }
}
