package nl.smartworkx.admin.interfaces.web.financialfact.inbox;

import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.application.financialfact.inbox.InboxFinancialFact;
import nl.smartworkx.admin.application.financialfact.inbox.InboxFinancialFactService;

/**
 *
 */
@RestController
@RequestMapping("/inbox-financial-facts")
@CrossOrigin
public class InboxFinancialFactsController {

    private final InboxFinancialFactService service;

    public InboxFinancialFactsController(InboxFinancialFactService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<InboxFinancialFact>> getInboxFinancialFacts() {
        Resources<InboxFinancialFact> resource = new Resources<>(service.getInboxFinancialFacts());
        return ResponseEntity.ok(resource);
    }
}
