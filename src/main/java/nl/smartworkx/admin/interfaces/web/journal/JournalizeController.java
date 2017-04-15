package nl.smartworkx.admin.interfaces.web.journal;

import static nl.smartworkx.admin.model.time.DateUtils.today;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.LedgerRepository;
import nl.smartworkx.admin.model.journal.Record;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/journal-entries")
@CrossOrigin
public class JournalizeController {

    private final JournalEntryRepository journalEntryRepository;
    private final LedgerRepository ledgerRepository;

    @Autowired
    public JournalizeController(final JournalEntryRepository journalEntryRepository, LedgerRepository ledgerRepository) {

        this.journalEntryRepository = journalEntryRepository;
        this.ledgerRepository = ledgerRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody JournalizeForm form) {

        JournalEntry journalEntry = convert(form);
        journalEntryRepository.save(journalEntry);
        return ResponseEntity.ok().build();
    }

    private JournalEntry convert(final JournalizeForm form) {

        List<Record> eur = form.getRecords().stream()
                .map(recordFormLine -> new Record(ledgerRepository.findByCode(recordFormLine.getLedger()).getId(), recordFormLine.getDebitCredit(),
                        new Amount(recordFormLine.getAmount()))).collect(Collectors.toList());
        return new JournalEntry(today(), form.getFinancialFactId(),
                eur);
    }

}
