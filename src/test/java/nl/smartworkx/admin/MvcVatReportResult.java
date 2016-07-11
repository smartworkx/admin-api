package nl.smartworkx.admin;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class MvcVatReportResult {
	private ResultActions resultActions;

	public MvcVatReportResult(final ResultActions perform) {

		resultActions = perform;
	}

	public void assertServicesTaxed(final String servicesTaxedAmount) throws Exception {

		resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.taxedAmount", Matchers.equalTo(servicesTaxedAmount)));

	}

	public void assertDeductedVatOf(final String deductedVat) throws Exception {

		resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.taxPaid", Matchers.equalTo(deductedVat)));
	}
}
