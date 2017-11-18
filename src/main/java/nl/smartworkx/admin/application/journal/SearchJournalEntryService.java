package nl.smartworkx.admin.application.journal;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

@Service
@AllArgsConstructor
@Slf4j
public class SearchJournalEntryService {
    private JournalEntryRepository repository;
    private LedgerRepository ledgerRepository;

    public Page<JournalEntry> search(final JournalEntryFilterCriteria fc, final Pageable pageable) {

        List<Long> ledgerIds = fc.getLedgers().stream().map(l -> ledgerRepository.findByCode(l).getId()).collect(Collectors.toList());
        return repository.findAll(
                where(JournalEntrySearchSpecs.hasValueDateGreaterThanOrEqualToDate(fc.getStartDate()))
                        .and(JournalEntrySearchSpecs.hasValueDateLessThanDate(fc.getEndDate()))
                        .and(JournalEntrySearchSpecs.hasLedger(ledgerIds)),
                pageable);
    }

}
