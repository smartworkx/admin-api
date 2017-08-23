package nl.smartworkx.admin.interfaces.web.journal;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalParameters;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalService;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/api/journal-entry-proposals")
@CrossOrigin
@AllArgsConstructor
public class JournalEntryProposalsController {

    private final JournalEntryProposalService journalEntryProposalService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity create(JournalEntryProposalParameters parameters) {
        return new ResponseEntity<>(new Resources<>(journalEntryProposalService.createProposedRecords(parameters)), HttpStatus.CREATED);
    }



}
