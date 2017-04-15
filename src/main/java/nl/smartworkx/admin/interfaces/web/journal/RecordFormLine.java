package nl.smartworkx.admin.interfaces.web.journal;

import java.math.BigDecimal;

import nl.smartworkx.admin.model.DebitCredit;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class RecordFormLine {
	private String ledger;

	private DebitCredit debitCredit;

	private BigDecimal amount;

	public String getLedger() {

		return ledger;
	}

	public void setLedger(final String ledger) {

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
