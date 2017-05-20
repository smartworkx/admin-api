package nl.smartworkx.admin.model.ledger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * Created by joris on 13-5-17.
 */
public class LedgerInstance {

    private Ledger ledger;
    private DatePeriod period;
    private List<LedgerInstanceLine> debitLines = new ArrayList<>();
    private List<LedgerInstanceLine> creditLines = new ArrayList<>();

    public List<LedgerInstanceLine> getDebitLines() {
        return debitLines;
    }

    public List<LedgerInstanceLine> getCreditLines() {
        return creditLines;
    }
}
