package nl.smartworkx.admin.model.balance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import nl.smartworkx.admin.model.DddAggregate;

/**
 *
 */
@Entity
public class Balance implements DddAggregate {
    @Id
    private Long id;
    private LocalDate date;
    private String description;

    @OneToMany
    private List<BalanceAccount> accounts;

    @Override
    public Object getId() {
        return id;
    }
}
