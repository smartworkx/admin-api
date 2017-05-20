package nl.smartworkx.admin.model.profitandlossstatement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * Created by joris on 15-5-17.
 */
@Entity
@Immutable
public class ProfitAndLossStatement implements DddAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profit_and_loss_statement")
    @SequenceGenerator(name = "profit_and_loss_statement", sequenceName = "profit_and_loss_statement_id_seq")
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDateTime;

    private String description;

    @Embedded
    private DatePeriod period;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "profit_and_loss_statement_id")
    private List<ProfitAndLossHeading> headings;

    public ProfitAndLossStatement(DatePeriod period, List<ProfitAndLossHeading> headings, String description) {
        this.description = description;
        this.period = period;
        this.headings = headings;
    }

    @Override
    public Object getId() {
        return id;
    }

    @Override
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getDescription() {
        return description;
    }

    public DatePeriod getPeriod() {
        return period;
    }

    public Amount getProfit() {
        return getAmount(DebitCredit.CREDIT).subtract(getAmount(DebitCredit.DEBIT));
    }

    private Amount getAmount(DebitCredit debitCredit) {
        return this.headings.stream().filter(profitAndLossHeading -> profitAndLossHeading.getName().getDebitCredit() == debitCredit).map
                (profitAndLossHeading -> profitAndLossHeading.getAmount()).reduce(Amount.ZERO, Amount::add);
    }

    public List<ProfitAndLossHeading> getHeadings() {
        return headings;
    }

    public Optional<ProfitAndLossHeading> getHeading(ProfitAndLossHeadingName name) {
        return headings.stream().filter(profitAndLossHeading -> profitAndLossHeading.getName().equals(name)).findFirst();
    }
}
