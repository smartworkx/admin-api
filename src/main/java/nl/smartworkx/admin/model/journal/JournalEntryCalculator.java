package nl.smartworkx.admin.model.journal;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import nl.smartworkx.admin.model.Amount;

public class JournalEntryCalculator {
    private LedgerRepository ledgerRepository;
    private Set<JournalEntryFinancialFact> entries;

    public JournalEntryCalculator(LedgerRepository ledgerRepository, Set<JournalEntryFinancialFact> entries) {
        this.ledgerRepository = ledgerRepository;
        this.entries = entries;
    }

    public Amount sum(String name) {
        return new Amount(getRecords(name).map(record -> record.getAmount().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add), Amount.DEFAULT_CURRENCY_CODE);
    }

    private Stream<Record> getRecords(String name) {

        return getJournalEntryStream()
                .flatMap(je -> je.getRecords().stream())
                .filter(r -> r.hasLedger(ledgerCodeToId(name)));
    }

    private Stream<JournalEntry> getJournalEntryStream() {
        return entries.stream()
                .map(JournalEntryFinancialFact::getJournalEntry);
    }

    public Set<Long> getJournalEntryIds(String... names) {
        Stream<JournalEntry> journalEntryStream = getJournalEntryStream().filter(je -> je.hasRecordMatching(ledgerCodeToIds(names)));
        Set<Long> set = journalEntryStream
                .map(JournalEntry::getId)
                .collect(toSet());
        return set;
    }

    private Set<Long> ledgerCodeToIds(String[] names) {
        return stream(names).map(s -> ledgerCodeToId(s)).collect(toSet());
    }

    private Long ledgerCodeToId(String s) {
        return ledgerRepository.findByCode(s).getId();
    }

    public Map<Ledger, List<Record>> getRecordsByLedger() {
        return entries.stream().flatMap(journalEntry -> journalEntry.getJournalEntry().getRecords().stream()).collect(groupingBy(o ->
                ledgerRepository.findOne(o
                        .getLedgerId())));
    }
}
