package nl.smartworkx.admin.model.journal;

import static nl.smartworkx.admin.model.time.DateUtils.today;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;

/**
 * Created by joris on 28-4-17.
 */
@Service
public class CreateJournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final LedgerRepository ledgerRepository;
    private final FinancialFactRepository financialFactRepository;

    public CreateJournalEntryService(JournalEntryRepository journalEntryRepository, LedgerRepository ledgerRepository, FinancialFactRepository financialFactRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.ledgerRepository = ledgerRepository;
        this.financialFactRepository = financialFactRepository;
    }

    public JournalEntry create(JournalEntryCreatedEvent event) {

        List<Record> eur = event.getRecords().stream()
                .map(recordFormLine -> new Record(ledgerRepository.findByCode(recordFormLine.getLedger()).getId(), recordFormLine.getDebitCredit(),
                        new Amount(recordFormLine.getAmount()))).collect(Collectors.toList());
        FinancialFact financialFact = financialFactRepository.findOne(event.getFinancialFactId());
        JournalEntry journalEntry = new JournalEntry(financialFact.getValueDate(), event.getFinancialFactId(),
                eur);

        return journalEntryRepository.save(journalEntry);
    }

}
