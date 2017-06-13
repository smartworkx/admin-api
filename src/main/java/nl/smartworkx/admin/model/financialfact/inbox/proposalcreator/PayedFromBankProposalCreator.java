package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import static nl.smartworkx.admin.model.financialfact.inbox.ProposalUtils.createRecordsFromBank;

import java.util.List;

import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

public abstract class PayedFromBankProposalCreator extends AbstractProposalCreator {
    @Override
    public boolean matches(FinancialFact financialFact) {
        return financialFact.getOrigin() != null && financialFact.getOrigin().getType().contains("ING_BANK_OPERATION") && financialFact.getDebitCredit().equals
                (DebitCredit.DEBIT) &&
                descriptionContainsAny(financialFact,descriptions());
    }

    protected abstract String[] descriptions();

    @Override
    public List<RecordFormLine> onCreate(FinancialFact financialFact) {
        final Amount amount = financialFact.getAmount();
        return createRecordsFromBank(ledgerRepository, amount, counterLedger());
    }

    protected String counterLedger() {
        return "CRED";
    }
}
