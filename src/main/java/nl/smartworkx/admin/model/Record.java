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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record")
	@SequenceGenerator(name = "record", sequenceName = "record_id_seq")
	private Long id;

	private Long ledger;

	@Enumerated
	private DebitCredit debitCredit;

	@Convert(converter = MonetaryAmountConverter.class)
	@Column(columnDefinition="varchar(64)")
	private MonetaryAmount amount;

	private Record() {

	}

	public Record(Long ledger, DebitCredit debitCredit, MonetaryAmount amount) {

		this.ledger = ledger;
		this.debitCredit = debitCredit;

		this.amount = amount;
	}

	public Long getLedger() {

		return ledger;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public MonetaryAmount getAmount() {

		return amount;
	}
}
