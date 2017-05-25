package nl.smartworkx.admin.model.balance;

import java.util.List;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.balance.BalanceAccountDetails;
import nl.smartworkx.admin.model.balance.BalanceHeadingName;

/**
 * Created by joris on 22-5-17.
 */
public class BalanceHeading {
    private BalanceHeadingName name;
    private List<BalanceAccountDetails> accounts;

    public BalanceHeading(BalanceHeadingName name, List<BalanceAccountDetails> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public BalanceHeadingName getName() {
        return name;
    }

    public List<BalanceAccountDetails> getAccounts() {
        return accounts;
    }

    public DebitCredit getDebitCredit() {
        return name.getDebitCredit();
    }

    public Amount getAmount() {
        return this.getAccounts().stream().map(BalanceAccountDetails::getAmount).reduce(Amount.ZERO, Amount::add);
    }
}
