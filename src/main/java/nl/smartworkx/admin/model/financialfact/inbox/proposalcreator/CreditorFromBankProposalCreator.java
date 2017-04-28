package nl.smartworkx.admin.model.financialfact.inbox.proposalcreator;

import org.springframework.stereotype.Component;

/**
 * Created by joris on 28-4-17.
 */
@Component
public class CreditorFromBankProposalCreator extends PayedFromBankProposalCreator {

    @Override
    protected String[] descriptions() {
        return descriptions("ing commercial cards", "it in zicht haarlem", "simyo", "transip");
    }
}
