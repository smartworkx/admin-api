package nl.smartworkx.admin.model.financialfact.inbox;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.TaxRate;

/**
 *
 */
public class JournalEntryProposalParameters {
    private ProposalType type;
    private BigDecimal amount;
    private TaxRate taxRate;

    public ProposalType getType() {
        return type;
    }

    public void setType(ProposalType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TaxRate getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(TaxRate taxRate) {
        this.taxRate = taxRate;
    }
}
