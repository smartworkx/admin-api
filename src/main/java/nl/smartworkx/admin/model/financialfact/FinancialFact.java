package nl.smartworkx.admin.model.financialfact;

import static java.time.LocalDateTime.now;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Immutable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
@Immutable
public class FinancialFact implements DddAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_fact")
    @SequenceGenerator(name = "financial_fact", sequenceName = "financial_fact_id_seq")
    private Long id;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDateTime;

    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate valueDate;

    @NotNull
    @Embedded
    private Amount amount;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private DebitCredit debitCredit;

    @Embedded
    private FinancialFactOrigin origin;

    private FinancialFact() {
        this.creationDateTime = now();
    }

    public FinancialFact(LocalDate valueDate,
            Amount amount,
            String description, FinancialFactOrigin origin) {
        this();
        this.valueDate = valueDate;
        this.amount = amount;
        this.description = description;
        this.origin = origin;
    }

    @Builder
    public FinancialFact(LocalDate valueDate, Amount amount, String description,
            DebitCredit debitCredit, FinancialFactOrigin origin) {

        this(valueDate, amount, description, origin);
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

    public FinancialFactOrigin getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinancialFact that = (FinancialFact) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
}
