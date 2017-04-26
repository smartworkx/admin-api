package nl.smartworkx.admin.model.journal;

import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 * Created by joris on 26-4-17.
 */
public class JournalEntryFinancialFact {
    private final JournalEntry journalEntry;
    private final FinancialFact financialFact;

    public JournalEntryFinancialFact(JournalEntry journalEntry, FinancialFact financialFact) {
        this.journalEntry = journalEntry;
        this.financialFact = financialFact;
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public FinancialFact getFinancialFact() {
        return financialFact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JournalEntryFinancialFact that = (JournalEntryFinancialFact) o;

        if (!journalEntry.equals(that.journalEntry)) {
            return false;
        }
        return financialFact.equals(that.financialFact);
    }

    @Override
    public int hashCode() {
        int result = journalEntry.hashCode();
        result = 31 * result + financialFact.hashCode();
        return result;
    }
}
