package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils.createRecordsFromBank;

import java.util.List;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.inbox.proposalcreator.AbstractProposalCreator;

/**
 *
 */
@Component
public class PrivateFromBankProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return descriptionContainsAny(financialFact, "omzet", "voorschot", "wijlens", "ziggo", "prive") && DebitCredit.DEBIT.equals(financialFact.getDebitCredit
                ());
    }


    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        return createRecordsFromBank(ledgerRepository, financialFact.getAmount(), "PRIVJ");
    }

}
