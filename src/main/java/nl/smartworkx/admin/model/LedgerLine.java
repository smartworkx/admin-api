package nl.smartworkx.admin.model;


import nl.smartworkx.admin.infrastructure.jpa.MonetaryAmountConverter;

import javax.money.MonetaryAmount;
import javax.persistence.*;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class LedgerLine extends BaseEntity{
	@ManyToOne
	private Ledger ledger;

	@Enumerated
	private DebitCredit debitCredit;

	@Convert(converter = MonetaryAmountConverter.class)
	@Column(columnDefinition="varchar(64)")
	private MonetaryAmount amount;

	public LedgerLine(Ledger ledger, DebitCredit debitCredit, MonetaryAmount amount) {

		this.ledger = ledger;
		this.debitCredit = debitCredit;

		this.amount = amount;
	}

	public Ledger getLedger() {

		return ledger;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public MonetaryAmount getAmount() {

		return amount;
	}
}
