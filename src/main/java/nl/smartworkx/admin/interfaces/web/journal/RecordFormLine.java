package nl.smartworkx.admin.interfaces.web.journal;

import java.math.BigDecimal;

import nl.smartworkx.admin.model.DebitCredit;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class RecordFormLine {
	private Long ledger;

	private DebitCredit debitCredit;

	private BigDecimal amount;

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

	public BigDecimal getAmount() {

		return amount;
	}

	public void setAmount(final BigDecimal amount) {

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
