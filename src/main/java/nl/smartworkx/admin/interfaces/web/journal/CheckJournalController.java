package nl.smartworkx.admin.interfaces.web.journal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.Record;

@RestController
@RequestMapping("/invalid-journal-entries")
@CrossOrigin
@AllArgsConstructor
public class CheckJournalController {

    private JournalEntryRepository journalEntryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List create() {
        List<Long> invalids = new ArrayList<>();
        journalEntryRepository.findAll().forEach(journalEntry -> {
            final Amount sum = Record.sum(journalEntry.getRecords(), DebitCredit.DEBIT);
            if(!sum.equals(Amount.ZERO)) {
                invalids.add(journalEntry.getId());
            }
        });
        return invalids;
    }
}
