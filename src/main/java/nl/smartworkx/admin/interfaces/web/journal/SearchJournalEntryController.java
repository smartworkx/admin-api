package nl.smartworkx.admin.interfaces.web.journal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.smartworkx.admin.application.journal.JournalEntryFilterCriteria;
import nl.smartworkx.admin.application.journal.SearchJournalEntryService;
import nl.smartworkx.admin.model.journal.JournalEntry;

@RepositoryRestController
@Slf4j
@AllArgsConstructor
public class SearchJournalEntryController {

    private SearchJournalEntryService service;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    @RequestMapping(method = RequestMethod.GET, value = "/journal-entries")
    @ResponseBody
    public PagedResources<PersistentEntityResource> search(JournalEntryFilterCriteria filterCriteria, Pageable
            pageable, PersistentEntityResourceAssembler resourceAssembler) {

        Page<JournalEntry> searchResult = service.search(filterCriteria, pageable);
        return pagedResourcesAssembler.toResource(searchResult, resourceAssembler);
    }
}
