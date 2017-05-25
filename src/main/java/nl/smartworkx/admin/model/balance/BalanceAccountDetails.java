package nl.smartworkx.admin.model.balance;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.balance.BalanceAccount;
import nl.smartworkx.admin.model.ledger.Ledger;

/**
 * Created by joris on 22-5-17.
 */
public class BalanceAccountDetails {
    private final BalanceAccount balanceAccount;
    private final Ledger ledger;

    public BalanceAccountDetails(BalanceAccount balanceAccount, Ledger ledger) {
        this.balanceAccount = balanceAccount;
        this.ledger = ledger;
    }

    public String getName(){
        return ledger.getName();
    }

    public Amount getAmount(){
        return balanceAccount.getAmount();
    }

}
