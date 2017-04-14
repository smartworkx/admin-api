package nl.smartworkx.admin;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import nl.smartworkx.admin.glue.integration.FinancialFactStepdefs;

/**
 *
 */
public class BaseMvcResult {
    protected ResultActions resultActions;

    public BaseMvcResult(final ResultActions perform) {
        resultActions = perform;
    }

    public void arrayHasSizeOf(int i) throws Exception {
        resultActions.andExpect(jsonPath("$", hasSize(i)));
    }

    public ResultActions andExpect(ResultMatcher matcher) throws Exception {
        return resultActions.andExpect(matcher);
    }

    public ResultActions returnsOk() throws Exception {
        return andExpect(status().isOk());
    }
}
