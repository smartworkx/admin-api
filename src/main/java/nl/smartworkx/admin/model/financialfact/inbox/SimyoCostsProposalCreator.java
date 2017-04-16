package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class SimyoCostsProposalCreator extends PayedFromBankWithVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "simyo";
    }

    @Override
    protected String costLedger() {
        return "TELC";
    }

}
