package nl.smartworkx.admin.model.financialfact.inbox;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nl.smartworkx.admin.model.Amount;

/**
 *
 */
public class JournalEntryProposalParameters {
    private ProposalType type;
    private BigDecimal amount;
    private Double taxRate;

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

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
