package nl.smartworkx.admin.adapters.tax.vat;

import nl.smartworkx.admin.model.Quarter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class VatReport {
	private final Quarter period;

	private final String taxedAmount;

	private final String taxPaid;

	public VatReport(Quarter period, String taxedAmount, String taxPaid) {

		this.period = period;
		this.taxedAmount = taxedAmount;
		this.taxPaid = taxPaid;
	}

	public Quarter getPeriod() {

		return period;
	}

	public String getTaxedAmount() {

		return taxedAmount;
	}

	public String getTaxPaid() {

		return taxPaid;
	}
}
