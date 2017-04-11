package nl.smartworkx.admin.model.journal;

import java.util.List;

import nl.smartworkx.admin.model.Quarter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface JournalEntryRepositoryCustom {

	List<Object[]> findJournalEntriesForVatReport(Quarter quarter);
}
