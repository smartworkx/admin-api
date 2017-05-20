package nl.smartworkx.admin.model.balance;

import static nl.smartworkx.admin.model.journal.Record.sum;

import nl.smartworkx.admin.model.journal.*;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
import nl.smartworkx.admin.model.time.DatePeriod;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class BalanceFactory {

    private final LedgerRepository ledgerRepository;

    private final JournalEntryRepository journalEntryRepository;

    private final BalanceRepository balanceRepository;

    public BalanceFactory(LedgerRepository ledgerRepository, JournalEntryRepository journalEntryRepository, BalanceRepository balanceRepository) {
        this.ledgerRepository = ledgerRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.balanceRepository = balanceRepository;
    }

    public Balance create(LocalDate date, String description) {
        Balance previousBalance = balanceRepository.findPreviousBalance(date);

        Set<JournalEntryFinancialFact> journalEntriesByDate = journalEntryRepository.findJournalEntriesByDate(new DatePeriod(previousBalance.getDate(), date));

        JournalEntryCalculator calculator = new JournalEntryCalculator(ledgerRepository, journalEntriesByDate);

        Set<BalanceAccount> balanceAccounts = calculator.getRecordsByLedger().entrySet().stream().filter(e -> e.getKey().shouldShowOnBalance()).map(e -> new
                BalanceAccount(e
                .getKey(), sum(e
                .getValue()))).collect(Collectors.toSet());
        return new Balance(date, description, balanceAccounts);
    }
}
