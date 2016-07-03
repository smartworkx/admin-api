package nl.smartworkx.admin.model;

import java.time.LocalDate;
import javax.money.MonetaryAmount;
import javax.persistence.*;

import nl.smartworkx.admin.infrastructure.jpa.MonetaryAmountConverter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class FinancialFact implements DddAggregate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_fact")
	@SequenceGenerator(name = "financial_fact", sequenceName = "financial_fact_id_seq")
	private Long id;

	private LocalDate valueDate;

	@Convert(converter = MonetaryAmountConverter.class)
	@Column(columnDefinition = "varchar(64)")
	private MonetaryAmount amount;

	private String description;

	private DebitCredit debitCredit;

	private FinancialFact() {

	}

	public FinancialFact(LocalDate valueDate,
			MonetaryAmount amount,
			String description) {

		this.valueDate = valueDate;
		this.amount = amount;
		this.description = description;
	}

	public FinancialFact(LocalDate valueDate, MonetaryAmount amount, String description,
			DebitCredit debitCredit) {

		this(valueDate, amount, description);
		this.debitCredit = debitCredit;
	}

	public LocalDate getValueDate() {

		return valueDate;
	}

	public MonetaryAmount getAmount() {

		return amount;
	}

	public String getDescription() {

		return description;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public FinancialFactId getId() {

		return new FinancialFactId(id);
	}
}
