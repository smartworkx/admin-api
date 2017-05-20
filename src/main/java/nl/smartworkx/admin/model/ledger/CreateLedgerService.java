package nl.smartworkx.admin.model.ledger;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * Created by joris on 13-5-17.
 */
@Service
@AllArgsConstructor
public class CreateLedgerService {

    private final JournalEntryRepository journalEntryRepository;

    public LedgerInstance createLedger(Ledger ledger, DatePeriod period) {

        //List<JournalEntry> journalEntries = journalEntryRepository.findByLedgerAndValueDateBetween(ledger,period);

        return new LedgerInstance();
    }
}
