package nl.smartworkx.admin.model.balance;

import javax.persistence.*;

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
    @GeneratedValue
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
