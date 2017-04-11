package nl.smartworkx.admin.model.journal;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;

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

    @Enumerated(EnumType.STRING)
    private DebitCredit debitCredit;

    @Embedded
    private Amount amount;

    private Record() {

    }

    public Record(Long ledger, DebitCredit debitCredit, Amount amount) {

        this.ledger = ledger;
        this.debitCredit = debitCredit;

        this.amount = amount;
    }

    public Record(Long id, DebitCredit debit, MonetaryAmount amount) {
        this(id, debit, new Amount(new BigDecimal(amount.getNumber().doubleValue()), amount.getCurrency().getCurrencyCode()));
    }

    public Long getLedger() {

        return ledger;
    }

    public DebitCredit getDebitCredit() {

        return debitCredit;
    }

    public Amount getAmount() {

        return amount;
    }
}
