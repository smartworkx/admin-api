package nl.smartworkx.admin.model.balance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 *
 */
@Entity
@Immutable
public class Balance implements DddAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "balance")
    @SequenceGenerator(name = "balance", sequenceName = "balance_id_seq")
    private Long id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDateTime;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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
    @JsonIgnore
    public Set<BalanceAccount> getAccounts() {
        return accounts;
    }

    public BalanceAccount findBalanceAccountByLedger(Ledger ledger) {
        return getAccounts().stream().filter(balanceAccount -> balanceAccount.getLedgerId().equals(ledger.getId())).findFirst().orElse(null);
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }


}
