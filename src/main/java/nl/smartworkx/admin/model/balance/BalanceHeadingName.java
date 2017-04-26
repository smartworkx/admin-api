package nl.smartworkx.admin.model.balance;

import nl.smartworkx.admin.model.DebitCredit;

/**
 *
 */
public enum BalanceHeadingName {
    // CREDIT
    VENTURE_CAPITAL(DebitCredit.CREDIT), PROVISION(DebitCredit.CREDIT), LONG_RUNNING_DEBT(DebitCredit.CREDIT), SHORT_RUNNING_DEBT(DebitCredit.CREDIT),
    // DEBIT
    LIQUID_ASSETS(DebitCredit.DEBIT), CLAIMS(DebitCredit.DEBIT), SUPPLIES(DebitCredit.DEBIT), FIXED_ASSETS(DebitCredit.DEBIT), TANGIBLE_FIXED_ASSETS(DebitCredit
            .DEBIT), INTANGILE_FIXED_ASSETS(DebitCredit.DEBIT), LOAN(DebitCredit.DEBIT);


    private DebitCredit debitCredit;

    BalanceHeadingName(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
    }
}
