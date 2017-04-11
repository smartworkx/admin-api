package nl.smartworkx.admin;

import org.springframework.stereotype.Component;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class FinancialFactMvcTestHelper extends BaseMvcTestHelper {

	public InboxFinancialFactsMvcResult getInboxFinancialFacts() throws Exception {

		return new InboxFinancialFactsMvcResult(
				performGet( "/inbox-financial-facts"));
	}

}
