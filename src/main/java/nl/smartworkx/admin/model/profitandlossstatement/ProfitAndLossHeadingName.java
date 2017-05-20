package nl.smartworkx.admin.model.profitandlossstatement;

import nl.smartworkx.admin.model.DebitCredit;

/**
 * Created by joris on 16-5-17.
 */
public enum ProfitAndLossHeadingName {
    TURNOVER(DebitCredit.CREDIT), EXTERNAL_COSTS(DebitCredit.DEBIT), PERSONAL_COSTS(DebitCredit.DEBIT), WRITE_OFF(DebitCredit.DEBIT),
    VALUE_CHANGES(DebitCredit.DEBIT), TRAVEL_COSTS(DebitCredit.DEBIT), OTHER_COSTS(DebitCredit.DEBIT), INTEREST(DebitCredit.DEBIT);

    private DebitCredit debitCredit;

    ProfitAndLossHeadingName(DebitCredit debitCredit) {
        this.debitCredit = debitCredit;
    }

    public DebitCredit getDebitCredit() {
        return debitCredit;
    }
}
