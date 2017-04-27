package nl.smartworkx.admin.adapters.tax.vat.interfaces.web.vatdeclaration;

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
import nl.smartworkx.admin.adapters.tax.vat.model.CreateVatDeclarationService;
import nl.smartworkx.admin.adapters.tax.vat.model.VatDeclaration;
import nl.smartworkx.admin.model.Quarter;

@RestController
@RequestMapping("/vat-declarations")
@CrossOrigin
public class VatDeclarationController {

    private final CreateVatDeclarationService createVatDeclarationService;

    public VatDeclarationController(CreateVatDeclarationService createVatDeclarationService) {
        this.createVatDeclarationService = createVatDeclarationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Quarter form) {

        VatDeclaration vatDeclaration = createVatDeclarationService.create(form);

        return new ResponseEntity<>(new Resource(vatDeclaration), HttpStatus.CREATED);
    }

}
