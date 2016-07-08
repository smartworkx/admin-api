package nl.smartworkx.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class MvcVatReportHelper {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	private MockMvc mvc() {

		if (mvc == null) {
			mvc = MockMvcBuilders
					.webAppContextSetup(context)
					.build();
		}
		return mvc;
	}

	public MvcVatReportResult getVatReport(final int year, final int quarterNumber) throws Exception {

		return new MvcVatReportResult(
				mvc().perform(MockMvcRequestBuilders.get("/vatReports?year=" + year + "&quarter=" + quarterNumber)));
	}
}
