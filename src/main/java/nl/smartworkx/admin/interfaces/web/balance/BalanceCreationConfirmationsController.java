package nl.smartworkx.admin.interfaces.web.balance;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.balance.BalanceCreationCommand;
import nl.smartworkx.admin.model.balance.BalanceDetails;
import nl.smartworkx.admin.model.balance.CreateBalanceService;

@RestController
@AllArgsConstructor
@RequestMapping("/balance-creation-confirmations")
public class BalanceCreationConfirmationsController {

    private CreateBalanceService createBalanceService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody BalanceCreationCommand event) {

        final BalanceDetails balanceDetails = createBalanceService.confirmBalanceDetails(event);
        return new ResponseEntity<>(new Resource(balanceDetails), HttpStatus.CREATED);
    }
}
