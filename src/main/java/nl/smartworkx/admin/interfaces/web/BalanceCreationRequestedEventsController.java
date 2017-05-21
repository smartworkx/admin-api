package nl.smartworkx.admin.interfaces.web;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.balance.Balance;
import nl.smartworkx.admin.model.balance.BalanceFactory;

/**
 * Created by joris on 21-5-17.
 */
@RestController
@AllArgsConstructor
@RequestMapping("balance-creation-requested-events")
public class BalanceCreationRequestedEventsController {

    private BalanceFactory balanceFactory;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody BalanceCreationRequestedEvent event) {

        Balance balance = balanceFactory.create(event);

        return new ResponseEntity<>(new Resource(balance), HttpStatus.CREATED);
    }
}
