package nl.smartworkx.admin.model.profitandlossstatement;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.journal.JournalEntryCalculator;
import nl.smartworkx.admin.model.journal.JournalEntryFinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.Record;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * Created by joris on 16-5-17.
 */
@Component
@AllArgsConstructor
public class ProfitAndLossStatementFactory {

    private final LedgerRepository ledgerRepository;

    private final JournalEntryRepository journalEntryRepository;

    @Transactional
    public ProfitAndLossStatement create(ProfitAndLossStatementCreationRequestedEvent event) {
        DatePeriod period = event.getPeriod();
        final Set<JournalEntryFinancialFact> journalEntryFinancialFacts = journalEntryRepository.findJournalEntriesByDate(period);

        JournalEntryCalculator calculator = new JournalEntryCalculator(ledgerRepository, journalEntryFinancialFacts);

        List<ProfitAndLossHeading> profitAndLossHeadings = ledgerRepository.findAll().stream().filter(ledger -> ledger.shouldShowOnProfitAndLossStatement())
                .collect(groupingBy(ledger -> ledger.getProfitAndLossHeading())).entrySet().stream().map(entry -> {
                    final Map<Ledger, List<Record>> recordsByLedger = calculator.getRecordsByLedger();
                    return new ProfitAndLossHeading(entry.getKey(), entry.getValue().stream().flatMap(ledger -> {
                        final List<Record> records = recordsByLedger.get(ledger);
                        return records != null ? records.stream() : null;
                    }).collect
                            (toList()));
                }).collect(toList());

        final ProfitAndLossStatement profitAndLossStatement = new ProfitAndLossStatement(period, profitAndLossHeadings, "");

        return profitAndLossStatement;
    }
}
