package nl.smartworkx.admin.adapters.tax.vat.model;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.Quarter;
import nl.smartworkx.admin.model.time.DateUtils;

@Entity
@Immutable
public class VatDeclaration implements DddAggregate {
    @Id
    private Long id;

    @Embedded
    private Quarter period;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "vat_declaration_id")
    private Set<VatJournalEntry> journalEntries;

    private VatDeclaration() {
        this.creationDateTime = DateUtils.now();
    }

    VatDeclaration(Quarter period, Amount vatServicedAmount, Amount vatDeductedAmount, Set<VatJournalEntry> journalEntries) {
        this();
        this.period = period;
        this.vatServicedAmount = vatServicedAmount;
        this.vatDeductedAmount = vatDeductedAmount;
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
}
