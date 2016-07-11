package nl.smartworkx.admin.glue.integration;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import nl.smartworkx.admin.MvcVatReportHelper;
import nl.smartworkx.admin.MvcVatReportResult;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class VatReportStepdefs {

	@Autowired
	private MvcVatReportHelper mvcVatReportHelper;

	private MvcVatReportResult vatReportResult;

	@And("^the entrepreneur asks for the VAT report for the (\\d+)nd quarter of (\\d+)$")
	public void theEntrepreneurAsksForTheVATReportForTheNdQuarterOf(int quarterNumber, int year) throws Throwable {

		vatReportResult = mvcVatReportHelper.getVatReport(year, quarterNumber);
	}

	@Then("^the vat report has an services taxed with high rate of \"([^\"]*)\"$")
	public void theVatReportHasAnServicesTaxedWithHighRateOf(String servicesTaxedAmount) throws Throwable {

		vatReportResult.assertServicesTaxed(servicesTaxedAmount);
	}

	@And("^the vat report has a deducted VAT of \"([^\"]*)\"$")
	public void theVatReportHasADeductedVATOf(String deductedVat) throws Throwable {

		vatReportResult.assertDeductedVatOf(deductedVat);
	}
}
