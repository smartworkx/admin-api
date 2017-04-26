package nl.smartworkx.admin.model.balance;

import javax.persistence.*;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.Ledger;

/**
 *
 */
@Entity
public class BalanceAccount {

    @Id
    @GeneratedValue
    private Long id;

    private Long ledgerId;

    private Amount amount;

    private BalanceAccount() {
    }

    public BalanceAccount(Ledger ledgerId, Amount amount) {
        this.ledgerId = ledgerId.getId();
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getLedgerId() {
        return ledgerId;
    }

    public Amount getAmount() {
        return amount;
    }
}
