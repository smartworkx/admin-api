package nl.smartworkx.admin.model.financialfact;

import java.time.LocalDate;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.experimental.Builder;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.DebitCredit;

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

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate valueDate;

	@Embedded
	private Amount amount;

	private String description;

	private DebitCredit debitCredit;

	private FinancialFact() {}

	public FinancialFact(LocalDate valueDate,
			Amount amount,
			String description) {

		this.valueDate = valueDate;
		this.amount = amount;
		this.description = description;
	}

	@Builder
	public FinancialFact(LocalDate valueDate, Amount amount, String description,
			DebitCredit debitCredit) {

		this(valueDate, amount, description);
		this.debitCredit = debitCredit;
	}

	public LocalDate getValueDate() {

		return valueDate;
	}

	public Amount getAmount() {

		return amount;
	}

	public String getDescription() {

		return description;
	}

	public DebitCredit getDebitCredit() {

		return debitCredit;
	}

	public Long getId() {

		return id;
	}
}
