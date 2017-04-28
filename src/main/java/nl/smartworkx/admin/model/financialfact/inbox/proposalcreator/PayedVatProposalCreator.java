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
public class PayedVatProposalCreator extends PayedFromBankProposalCreator {

    @Override
    protected String[] descriptions() {
        return descriptions("belastingdienst");
    }

    @Override
    protected String counterLedger() {
        return "VATP";
    }
}
