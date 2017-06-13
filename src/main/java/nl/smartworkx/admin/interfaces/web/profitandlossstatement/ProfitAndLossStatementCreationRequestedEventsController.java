package nl.smartworkx.admin.interfaces.web.profitandlossstatement;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.profitandlossstatement.ProfitAndLossStatementFactory;
import nl.smartworkx.admin.model.profitandlossstatement.ProfitAndLossStatement;
import nl.smartworkx.admin.model.profitandlossstatement.ProfitAndLossStatementCreationRequestedEvent;

/**
 * Created by joris on 21-5-17.
 */
@RestController
@AllArgsConstructor
@RequestMapping("profit-and-loss-statement-creation-requested-events")
@CrossOrigin
public class ProfitAndLossStatementCreationRequestedEventsController {

    private final ProfitAndLossStatementFactory service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody ProfitAndLossStatementCreationRequestedEvent event) {

        final ProfitAndLossStatement profitAndLossStatement = service.create(event);
        return new ResponseEntity<>(new Resource(profitAndLossStatement), HttpStatus.CREATED);
    }

}
