package nl.smartworkx.admin.adapters.tax.vat.model;

import static java.util.stream.Collectors.toSet;
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

        final Amount vatServiced = calculator.sum(VATS);
        final Amount deductedVat = calculator.sum(DVAT);
        Set<VatJournalEntry> vatJournalEntries = calculator.getJournalEntryIds(DVAT, VATS).stream().map(VatJournalEntry::new).collect
                (toSet());
        return new VatDeclaration(period, vatServiced, deductedVat, vatJournalEntries);
    }
}
