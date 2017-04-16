package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class KnowItCostsProposalCreator extends PayedFromBankWithVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "it in zicht haarlem";
    }

    @Override
    protected String costLedger() {
        return "EDUC";
    }

}
