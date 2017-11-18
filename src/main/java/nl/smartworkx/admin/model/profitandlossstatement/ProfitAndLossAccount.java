package nl.smartworkx.admin.model.profitandlossstatement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.ledger.Ledger;

/**
 *
 */
public class ProfitAndLossAccount {

    private Long ledgerId;

    private Amount amount;

    private ProfitAndLossAccount() {
    }

    ProfitAndLossAccount(Long ledger, Amount amount) {
        this.ledgerId = ledger;
        this.amount = amount;
    }

    public Long getLedgerId() {
        return ledgerId;
    }

    public Amount getAmount() {
        return amount;
    }
}
