package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class CreditCardCostsProposalCreator extends PayedFromBankWithoutVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "ing commercial cards";
    }

    @Override
    protected String costLedger() {
        return "CRED";
    }

}
