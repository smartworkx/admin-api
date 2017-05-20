package nl.smartworkx.admin;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import org.springframework.test.web.servlet.ResultActions;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.ledger.Ledger;

/**
 *
 */
public class InboxFinancialFactsMvcResult extends BaseMvcResult {
    public InboxFinancialFactsMvcResult(ResultActions perform) {
        super(perform);
    }

    public ResultActions getResultActions() {
        return resultActions;
    }

    public void hasRecord(UUID uuid, DebitCredit debitCredit, Amount amount, Ledger ledger) throws Exception {
        getResultActions().andExpect(jsonPath("$[?(@.financialFact.origin.uuid=='" + uuid +
                "')].records[?(@.ledger==" + ledger.getId() + " && @.debitCredit=='"+ debitCredit.toString()+ "' && @.amount.value==" + amount.getValue
                () + ")]", hasSize
                (1)));
    }
}
