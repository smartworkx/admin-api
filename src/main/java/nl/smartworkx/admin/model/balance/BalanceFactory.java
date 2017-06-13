package nl.smartworkx.admin.model.balance;

import static java.util.stream.Collectors.groupingBy;
import static nl.smartworkx.admin.model.journal.Record.sum;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.journal.*;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
import nl.smartworkx.admin.model.time.DatePeriod;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

        Map<Ledger, List<Record>> recordsByLedger = calculator.getRecordsByLedger();

        final Set<BalanceAccount> balanceAccounts = ledgerRepository.findAllBy()
                .filter(Ledger::shouldShowOnBalance)
                .map(ledger -> new BalanceAccount(ledger, calculateBalanceAmount(previousBalance
                        .findBalanceAccountByLedger(ledger).getAmount(), ledger
                        .getBalanceHeading().getDebitCredit(), recordsByLedger.get(ledger))))
                .collect(Collectors.toSet());

        return new Balance(date, description, balanceAccounts);
    }

    Amount calculateBalanceAmount(Amount startAmount, DebitCredit debitCredit, List<Record> records) {
        if(records == null){
            return startAmount;
        }
        final Map<DebitCredit, List<Record>> recordsGroupedByDebitCredit = records.stream().collect(groupingBy(o -> o.getDebitCredit()));

        if (debitCredit == DebitCredit.DEBIT) {
            return startAmount.add(sum(recordsGroupedByDebitCredit.get(DebitCredit.DEBIT))
                    .subtract(sum(recordsGroupedByDebitCredit.get(DebitCredit.CREDIT))));
        } else {
            return startAmount.add(sum(recordsGroupedByDebitCredit.get(DebitCredit.CREDIT))
                    .subtract(sum(recordsGroupedByDebitCredit.get(DebitCredit.DEBIT))));
        }
    }

    public Balance create(BalanceCreationRequestedEvent event) {
        return create(event.getDate(), event.getDescription());
    }
}
