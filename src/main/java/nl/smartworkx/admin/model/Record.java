package nl.smartworkx.admin.model;

import javax.money.MonetaryAmount;
import javax.persistence.*;

import nl.smartworkx.admin.infrastructure.jpa.MonetaryAmountConverter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Record {

	@Id
	@GeneratedValue
	private Long id;

	private LedgerId ledger;

	@Enumerated
	private DebitCredit debitCredit;

	@Convert(converter = MonetaryAmountConverter.class)
	@Column(columnDefinition="varchar(64)")
	private MonetaryAmount amount;

	public Record(LedgerId ledger, DebitCredit debitCredit, MonetaryAmount amount) {

		this.ledger = ledger;
		this.debitCredit = debitCredit;

		this.amount = amount;
	}

	public LedgerId getLedger() {

		return ledger;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public MonetaryAmount getAmount() {

		return amount;
	}
}
