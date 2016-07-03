package nl.smartworkx.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import nl.smartworkx.admin.model.FinancialFactId;
import nl.smartworkx.admin.model.JournalEntry;
import nl.smartworkx.admin.model.Record;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
class DomainJournalEntryHelper {

	public static JournalEntry createOutgoingInvoiceJournalEntry(FinancialFactId financialFactId, LocalDate bookDate) {

		List<Record> records = new ArrayList<>();
		return new JournalEntry(bookDate, financialFactId, records);
	}

	public static JournalEntry createIncomingInvoiceJournalEntry(final FinancialFactId id, final LocalDate now,
			final int taxRate) {

		return null;
	}
}
