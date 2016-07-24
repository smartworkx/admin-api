package nl.smartworkx.admin.web;

import nl.smartworkx.admin.model.DebitCredit;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class RecordFormLine {
	private Long ledger;

	private DebitCredit debitCredit;

	private Double amount;

	public Long getLedger() {

		return ledger;
	}

	public void setLedger(final Long ledger) {

		this.ledger = ledger;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public void setDebitCredit(final DebitCredit debitCredit) {

		this.debitCredit = debitCredit;
	}

	public Double getAmount() {

		return amount;
	}

	public void setAmount(final Double amount) {

		this.amount = amount;
	}

	@Override public String toString() {

		return "RecordFormLine{" +
				"ledger='" + ledger + '\'' +
				", debitCredit='" + debitCredit + '\'' +
				", amount='" + amount + '\'' +
				'}';
	}
}
