package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class TransipCostsProposalCreator extends PayedFromBankWithVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "transip";
    }

    @Override
    protected String costLedger() {
        return "ITC";
    }

}
