package nl.smartworkx.admin.model;

import java.util.List;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface JournalEntryRepositoryCustom {

	List<Object[]> findJournalEntriesForVatReport(Quarter quarter);
}
