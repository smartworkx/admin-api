package nl.smartworkx.admin.interfaces.web.balance;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.balance.BalanceCreationRequestedEvent;
import nl.smartworkx.admin.model.balance.BalanceDetails;
import nl.smartworkx.admin.model.balance.CreateBalanceService;

/**
 * Created by joris on 22-5-17.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/balance-details")
@CrossOrigin
public class BalanceDetailsController {
    private final CreateBalanceService createBalanceService;

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity findBalanceDetails(@PathVariable Long id) {

        final BalanceDetails balanceDetails = createBalanceService.createBalanceDetails(id);
        return new ResponseEntity<>(new Resource(balanceDetails), HttpStatus.OK);
    }
}
