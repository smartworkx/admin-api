package nl.smartworkx.admin.interfaces.web.journal;

import java.math.BigDecimal;

import javax.money.NumberValue;

import org.springframework.hateoas.core.Relation;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Relation(collectionRelation = "records")
public class RecordFormLine {
	private String ledger;

	private DebitCredit debitCredit;

	private BigDecimal amount;

	public RecordFormLine() {
	}

	public RecordFormLine(String ledger, DebitCredit debitCredit, Amount amount) {

		this.ledger = ledger;
		this.debitCredit = debitCredit;
		this.amount = amount.getValue();
	}

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
