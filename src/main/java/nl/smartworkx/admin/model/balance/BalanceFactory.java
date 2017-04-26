package nl.smartworkx.admin.model.balance;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
        List<BalanceAccount> balanceAccounts = new ArrayList<>();
        Balance previousBalance = balanceRepository.findPreviousBalance(date);

        Set<JournalEntryFinancialFact> journalEntriesByDate = journalEntryRepository.findJournalEntriesByDate(previousBalance.getDate(), date);

        journalEntriesByDate.stream().flatMap(journalEntry -> journalEntry.getJournalEntry().getRecords().stream()).collect(Collectors.groupingBy(o -> o
                .getLedgerId()))
                .forEach
                ((ledgerId, records) -> {
            Ledger ledger = ledgerRepository.findOne(ledgerId);
            BalanceHeadingName balanceHeading = ledger.getBalanceHeading();
            if (balanceHeading != null) {
                Amount sum = records.stream().map(record -> record.getAmount()).reduce(Amount.ZERO,(amount1, amount2) -> amount1.add(amount2));
                balanceAccounts.add(new BalanceAccount(ledger,sum));
            }
        });

        return new Balance(date, description, balanceAccounts);
    }

}
