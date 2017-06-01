package nl.smartworkx.admin.model.journal;

import java.util.List;
import java.util.stream.Collectors;


import javax.validation.Validator;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.infrastructure.validation.ValidationService;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

/**
 * Created by joris on 28-4-17.
 */
@Service
@AllArgsConstructor
public class CreateJournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final LedgerRepository ledgerRepository;
    private final FinancialFactRepository financialFactRepository;
    private final ValidationService validator;

    public JournalEntry create(JournalEntryCreatedEvent event) {

        List<Record> eur = event.getRecords().stream()
                .map(recordFormLine -> new Record(ledgerRepository.findByCode(recordFormLine.getLedger()).getId(), recordFormLine.getDebitCredit(),
                        new Amount(recordFormLine.getAmount()))).collect(Collectors.toList());
        FinancialFact financialFact = financialFactRepository.findOne(event.getFinancialFactId());
        JournalEntry journalEntry = new JournalEntry(financialFact.getValueDate(), event.getFinancialFactId(),
                eur);

        validator.validate(journalEntry);

        return journalEntryRepository.save(journalEntry);
    }

}
