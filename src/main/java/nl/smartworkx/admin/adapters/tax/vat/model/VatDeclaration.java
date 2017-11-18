package nl.smartworkx.admin.adapters.tax.vat.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
import nl.smartworkx.admin.model.time.DateUtils;
import nl.smartworkx.admin.model.time.Quarter;

@Entity
@Immutable
public class VatDeclaration implements DddAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vat_declaration")
    @SequenceGenerator(name = "vat_declaration", sequenceName = "vat_declaration_id_seq")
    private Long id;

    @Embedded
    private Quarter period;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime creationDateTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "vat_serviced_amount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "vat_serviced_amount_currency"))
    })
    private Amount vatServicedAmount;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "vat_deducted_amount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "vat_deducted_amount_currency"))
    })
    private Amount vatDeductedAmount;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "turnover_high_amount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "turnover_high_amount_currency"))
    })
    private Amount turnoverHigh;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vat_declaration_id")
    private Set<VatJournalEntry> journalEntries;

    private VatDeclaration() {
        this.creationDateTime = DateUtils.now();
    }

    VatDeclaration(Quarter period, Amount vatServicedAmount, Amount vatDeductedAmount, Amount turnoverHigh, Set<VatJournalEntry> journalEntries) {
        this();
        this.period = period;
        this.vatServicedAmount = vatServicedAmount;
        this.vatDeductedAmount = vatDeductedAmount;
        this.turnoverHigh = turnoverHigh;
        this.journalEntries = journalEntries;
    }

    public Quarter getPeriod() {
        return period;
    }

    @SuppressWarnings("WeakerAccess")
    public Amount getVatServicedAmount() {
        return vatServicedAmount;
    }

    public Amount getVatDeductedAmount() {
        return vatDeductedAmount;
    }

    @Override
    public Object getId() {
        return id;
    }

    public Set<VatJournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public Amount getTurnoverHigh() {
        return turnoverHigh;
    }
}
