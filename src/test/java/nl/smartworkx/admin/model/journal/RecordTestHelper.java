package nl.smartworkx.admin.model.journal;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;

public class RecordTestHelper {
    public static Record.RecordBuilder createAnonymous() {
        return Record.builder().ledgerId(1L).debitCredit(DebitCredit.DEBIT).amount(new Amount("11.00"));
    }
}

