package nl.smartworkx.admin.model.financialfact.inbox;

import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.TaxCalculator;

/**
 *
 */
@Component
public class NsProposalCreator extends PayedFromBankWithVatProposalCreator {

    @Override
    protected String descriptionContains() {
        return "ns-";
    }

    @Override
    protected String costLedger() {
        return "TRAC";
    }

    @Override
    protected double vatTaxRate() {
        return TaxCalculator.LOW;
    }
}
