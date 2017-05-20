package nl.smartworkx.admin.model.journal;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface JournalEntryRepositoryCustom {

	Set<JournalEntryFinancialFact> findJournalEntriesByDate(DatePeriod datePeriod);

}
