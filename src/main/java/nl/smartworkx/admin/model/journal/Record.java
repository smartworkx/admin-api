package nl.smartworkx.admin.model.journal;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import javax.money.MonetaryAmount;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.experimental.Builder;
import lombok.extern.java.Log;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
@Immutable
@Log
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record")
    @SequenceGenerator(name = "record", sequenceName = "record_id_seq")
    private Long id;

    private Long ledgerId;

    @Enumerated(EnumType.STRING)
    private DebitCredit debitCredit;

    @Embedded
    private Amount amount;

    private Record() {
    }

    @Builder
    public Record(Long ledgerId, DebitCredit debitCredit, Amount amount) {
        this.ledgerId = ledgerId;
        this.debitCredit = debitCredit;

        this.amount = amount;
    }

    public Record(Long id, DebitCredit debit, MonetaryAmount amount) {
        this(id, debit, new Amount(new BigDecimal(amount.getNumber().doubleValue()), amount.getCurrency().getCurrencyCode()));
    }

    public static Amount sum(List<Record> records, DebitCredit debitCredit) {
        return records == null ? Amount.ZERO : sum(records.stream(), debitCredit);
    }

    public static Amount sum(Stream<Record> records, DebitCredit debitCredit) {
        return records == null ? Amount.ZERO : new Amount(records.map(record -> {
            if (debitCredit == record.getDebitCredit()) {
                return record.getAmount().getValue();
            } else {
                return record.getAmount().getValue().negate();
            }
        }).reduce(BigDecimal.ZERO, BigDecimal::add), Amount.DEFAULT_CURRENCY_CODE);
    }

    @SuppressWarnings("WeakerAccess")
    public Long getLedgerId() {

        return ledgerId;
    }

    public DebitCredit getDebitCredit() {

        return debitCredit;
    }

    public Amount getAmount() {

        return amount;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", ledgerId=" + ledgerId +
                ", debitCredit=" + debitCredit +
                ", amount=" + amount +
                '}';
    }

    boolean hasLedger(Long ledgerId) {
        return getLedgerId().equals(ledgerId);
    }
}
