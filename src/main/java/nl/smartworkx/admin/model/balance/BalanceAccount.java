package nl.smartworkx.admin.model.balance;

import javax.persistence.Entity;
import javax.persistence.Id;

import nl.smartworkx.admin.model.Amount;

/**
 *
 */
@Entity
public class BalanceAccount {

    @Id
    private Long id;

    private Long ledger;

    private BalanceHeading balanceHeading;

    private Amount amount;
}
