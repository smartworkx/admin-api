package nl.smartworkx.admin;

import org.springframework.stereotype.Component;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class VatReportMvcTestHelper extends BaseMvcTestHelper {

	public VatReportMvcResult getVatReport(final int year, final int quarterNumber) throws Exception {

		return new VatReportMvcResult(
				performGet("/vatReports?year=" + year + "&quarter=" + quarterNumber));
	}

}
