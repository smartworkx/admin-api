package nl.smartworkx.admin;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class VatReportMvcResult extends BaseMvcResult {

	public VatReportMvcResult(final ResultActions perform) {
		super(perform);

	}

	public void assertServicesTaxed(final String servicesTaxedAmount) throws Exception {

		resultActions.andExpect(jsonPath("$.taxedAmount", equalTo(servicesTaxedAmount)));

	}

	public void assertDeductedVatOf(final String deductedVat) throws Exception {

		resultActions.andExpect(jsonPath("$.taxPaid", equalTo(deductedVat)));
	}
}
