package nl.smartworkx.admin.adapters.tax.vat.model;

import static nl.smartworkx.admin.model.journal.LedgerCode.DVAT;
import static nl.smartworkx.admin.model.journal.LedgerCode.VATS;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.JournalEntryCalculator;
import nl.smartworkx.admin.model.journal.JournalEntryFinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.LedgerCode;
import nl.smartworkx.admin.model.Quarter;
import nl.smartworkx.admin.model.journal.LedgerRepository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Service
public class VatDeclarationFactory {

    private JournalEntryRepository journalEntryRepository;
    private final LedgerRepository ledgerRepository;

    @Autowired
    public VatDeclarationFactory(final JournalEntryRepository journalEntryRepository, LedgerRepository ledgerRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public VatDeclaration create(final Quarter period) {

        Set<JournalEntryFinancialFact> entries = journalEntryRepository
                .findJournalEntriesByDate(period.getFirstDay(), period.getLastDay());
        JournalEntryCalculator calculator = new JournalEntryCalculator(ledgerRepository, entries);

        final Amount vatServiced = calculator.sum(VATS.name());
        final Amount deductedVat = calculator.sum(DVAT.name());
        Set<VatJournalEntry> vatJournalEntries = calculator.getJournalEntryIds(DVAT.name(), VATS.name()).stream().map(VatJournalEntry::new).collect(Collectors.toSet());
        return new VatDeclaration(period, vatServiced, deductedVat, vatJournalEntries);
    }
}
