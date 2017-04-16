package nl.smartworkx.admin.interfaces.web.journal;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalParameters;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalService;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/journal-entry-proposals")
@CrossOrigin
public class JournalEntryProposalsController {

    private final JournalEntryProposalService journalEntryProposalService;

    public JournalEntryProposalsController(JournalEntryProposalService journalEntryProposalService) {
        this.journalEntryProposalService = journalEntryProposalService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity create(JournalEntryProposalParameters parameters) {


        return new ResponseEntity<>(new Resource(journalEntryProposalService.createProposedRecords(parameters)), HttpStatus.CREATED);
    }



}
