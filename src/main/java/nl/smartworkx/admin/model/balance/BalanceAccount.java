package nl.smartworkx.admin.model.balance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.ledger.Ledger;

/**
 *
 */
@Entity
@Immutable
public class BalanceAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balance_account")
    @SequenceGenerator(name = "balance_account", sequenceName = "balance_account_id_seq")
    private Long id;

    private Long ledgerId;

    private Amount amount;

    private BalanceAccount() {
    }

    BalanceAccount(Ledger ledger, Amount amount) {
        this.ledgerId = ledger.getId();
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    Long getLedgerId() {
        return ledgerId;
    }

    public Amount getAmount() {
        return amount;
    }
}
