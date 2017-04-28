package nl.smartworkx.admin.interfaces.web.journal;

import static nl.smartworkx.admin.model.time.DateUtils.today;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.journal.CreateJournalEntryService;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryCreatedEvent;
import nl.smartworkx.admin.model.journal.Record;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/journal-entry-created-events")
@CrossOrigin
public class JournalEntryCreatedEventController {

    private final CreateJournalEntryService createJournalEntryService;

    public JournalEntryCreatedEventController(CreateJournalEntryService createJournalEntryService) {
        this.createJournalEntryService = createJournalEntryService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody JournalEntryCreatedEvent form) {


        return new ResponseEntity<>(new Resource(createJournalEntryService.create(form)), HttpStatus.CREATED);
    }

}
