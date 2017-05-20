package nl.smartworkx.admin.model.ledger;

import java.time.LocalDate;

import nl.smartworkx.admin.model.Amount;

/**
 * Created by joris on 13-5-17.
 */
public class LedgerInstanceLine {
    private LocalDate date;
    private String description;
    private Amount amount;

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }
}
