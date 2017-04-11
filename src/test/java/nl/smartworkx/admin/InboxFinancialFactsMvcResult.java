package nl.smartworkx.admin;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.ResultActions;
import nl.smartworkx.admin.glue.integration.FinancialFactStepdefs;

/**
 *
 */
public class InboxFinancialFactsMvcResult extends BaseMvcResult{
    public InboxFinancialFactsMvcResult(ResultActions perform) {
        super(perform);
    }

}
