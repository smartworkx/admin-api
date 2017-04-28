package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class BankAccountCostsProposalCreator extends PayedFromBankProposalCreator {

    @Override
    protected String[] descriptions() {
        return descriptions("kosten zakelijk betalingsverkeerfactuurnr");
    }

    @Override
    protected String counterLedger() {
        return "COSTS";
    }

}
