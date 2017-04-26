package nl.smartworkx.admin.model.journal;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface JournalEntryRepositoryCustom {

	Set<JournalEntryFinancialFact> findJournalEntriesByDate(LocalDate firstDay, LocalDate lastDay);
}
