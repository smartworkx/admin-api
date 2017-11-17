package nl.smartworkx.admin.adapters.tax.vat.interfaces.web.vatdeclaration;

import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.adapters.tax.vat.model.CreateVatDeclarationService;
import nl.smartworkx.admin.adapters.tax.vat.model.VatDeclaration;
import nl.smartworkx.admin.adapters.tax.vat.model.VatDeclarationFactory;
import nl.smartworkx.admin.model.balance.BalanceCreationCommand;
import nl.smartworkx.admin.model.balance.BalanceDetails;
import nl.smartworkx.admin.model.balance.CreateBalanceService;
import nl.smartworkx.admin.model.time.Quarter;

/**
 * Created by joris on 21-5-17.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/vat-declaration-creation-requests")
public class VatDeclarationCreationRequestsController {

    private final VatDeclarationFactory vatDeclarationFactory;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Quarter form) {
        return new ResponseEntity<>(new Resource(vatDeclarationFactory.create(form)), HttpStatus.CREATED);
    }

}
