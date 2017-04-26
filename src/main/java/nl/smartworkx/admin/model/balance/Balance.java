package nl.smartworkx.admin.model.balance;

import java.time.LocalDate;
import java.util.List;

import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.journal.Ledger;

import javax.persistence.*;

/**
 *
 */
@Entity
public class Balance implements DddAggregate {
    @Id
    private Long id;
    private LocalDate date;
    private String description;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "balance")
    private List<BalanceAccount> accounts;

    private Balance() {
    }

    public Balance(LocalDate date, String description, List<BalanceAccount> accounts) {
        this.date = date;
        this.description = description;
        this.accounts = accounts;
    }

    @Override
    public Object getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public List<BalanceAccount> getAccounts() {
        return accounts;
    }

    public BalanceAccount findBalanceAccountByLedger(Ledger ledger) {
        return getAccounts().stream().filter(balanceAccount -> balanceAccount.getLedgerId().equals(ledger.getId())).findFirst().orElse(null);
    }
}
