package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class BankAccountCostsProposalCreator extends PayedFromBankWithoutVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "kosten zakelijk betalingsverkeerfactuurnr";
    }

    @Override
    protected String costLedger() {
        return "COSTS";
    }

}
