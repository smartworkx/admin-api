package nl.smartworkx.admin.model.balance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.journal.Ledger;
import nl.smartworkx.admin.model.time.DateUtils;

import javax.persistence.*;

import org.hibernate.annotations.Immutable;

/**
 *
 */
@Entity
@Immutable
public class Balance implements DddAggregate {
    @Id
    private Long id;
    private LocalDateTime creationDateTime;
    private LocalDate date;
    private String description;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinColumn(name = "balance")
    private Set<BalanceAccount> accounts;

    private Balance() {
        this.creationDateTime = DateUtils.now();
    }

    Balance(LocalDate date, String description, Set<BalanceAccount> accounts) {
        this();
        this.date = date;
        this.description = description;
        this.accounts = accounts;
    }

    @Override
    public Object getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings("WeakerAccess")
    public Set<BalanceAccount> getAccounts() {
        return accounts;
    }

    BalanceAccount findBalanceAccountByLedger(Ledger ledger) {
        return getAccounts().stream().filter(balanceAccount -> balanceAccount.getLedgerId().equals(ledger.getId())).findFirst().orElse(null);
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
}
