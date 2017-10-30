package nl.smartworkx.admin.adapters.tax.vat.model;

import static java.util.stream.Collectors.toSet;
import static nl.smartworkx.admin.model.ledger.LedgerCode.DVAT;
import static nl.smartworkx.admin.model.ledger.LedgerCode.TOJ;
import static nl.smartworkx.admin.model.ledger.LedgerCode.VATS;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.JournalEntryCalculator;
import nl.smartworkx.admin.model.journal.JournalEntryFinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
import nl.smartworkx.admin.model.time.Quarter;

@Service
public class VatDeclarationFactory {

    private JournalEntryRepository journalEntryRepository;
    private final LedgerRepository ledgerRepository;

    @Autowired
    public VatDeclarationFactory(final JournalEntryRepository journalEntryRepository, LedgerRepository ledgerRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.ledgerRepository = ledgerRepository;
    }

    public VatDeclaration create(final Quarter quarter) {

        Set<JournalEntryFinancialFact> entries = journalEntryRepository
                .findJournalEntriesByDate(quarter.getPeriod());
        JournalEntryCalculator calculator = new JournalEntryCalculator(ledgerRepository, entries);

        final Amount vatServiced = calculator.sum(VATS);
        final Amount deductedVat = calculator.sum(DVAT);
        final Amount turnoverHigh = calculator.sum(TOJ);
        Set<VatJournalEntry> vatJournalEntries = calculator.getJournalEntryIds(DVAT, VATS).stream().map(VatJournalEntry::new).collect
                (toSet());
        return new VatDeclaration(quarter, vatServiced, deductedVat, turnoverHigh, vatJournalEntries);
    }
}
