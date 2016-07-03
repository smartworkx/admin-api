package nl.smartworkx.admin;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.FinancialFactId;
import nl.smartworkx.admin.model.JournalEntry;
import nl.smartworkx.admin.model.JournalEntryRepository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class RepositoryJournalEntryHelper {

	private JournalEntryRepository repository;

	@Autowired
	public RepositoryJournalEntryHelper(JournalEntryRepository repository) {

		this.repository = repository;
	}

	public JournalEntry createOutgoingInvoiceJournalEntry(FinancialFactId financialFactId, int taxRate,
			LocalDate date) {

		JournalEntry journalEntry = DomainJournalEntryHelper.createOutgoingInvoiceJournalEntry(financialFactId, date);
		repository.save(journalEntry);
		return journalEntry;

	}

	public JournalEntry createIncomingInvoiceJournalEntry(final FinancialFactId id, final int taxRate,
			final LocalDate now) {

		JournalEntry journalEntry = DomainJournalEntryHelper.createIncomingInvoiceJournalEntry(id, now, taxRate);
		repository.save(journalEntry);
		return null;
	}
}
