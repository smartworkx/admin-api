package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.financialfact.TaxRate;

/**
 *
 */
@Component
public class NsPinPaymentProposalCreator extends PayedFromBankWithVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "ns-";
    }

    @Override
    protected String counterLedger() {
        return "TRAC";
    }

    @Override
    protected TaxRate vatTaxRate() {
        return TaxRate.LOW;
    }
}
