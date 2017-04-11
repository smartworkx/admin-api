package nl.smartworkx.admin;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class FinancialFactTestHelper {
    public static FinancialFact.FinancialFactBuilder create() {
        return FinancialFact.builder().valueDate(DateUtils.today()).amount(new Amount(10.00)).description("bla").debitCredit(DebitCredit.CREDIT);
    }
}
