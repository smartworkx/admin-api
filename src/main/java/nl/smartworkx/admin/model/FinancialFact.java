package nl.smartworkx.admin.model;




import nl.smartworkx.admin.infrastructure.jpa.MonetaryAmountConverter;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class FinancialFact extends BaseEntity{

	private LocalDate valueDate;

	@Convert(converter = MonetaryAmountConverter.class)
	@Column(columnDefinition="varchar(64)")
	private MonetaryAmount amount;

	private String description;

	private DebitCredit debitCredit;

	public FinancialFact(LocalDate valueDate, MonetaryAmount amount, String description, DebitCredit debitCredit) {

		this.valueDate = valueDate;
		this.amount = amount;
		this.description = description;
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
}
