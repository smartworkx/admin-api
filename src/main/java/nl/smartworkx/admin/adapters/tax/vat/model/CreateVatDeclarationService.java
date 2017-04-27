package nl.smartworkx.admin.adapters.tax.vat.model;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.Quarter;

@Service
public class CreateVatDeclarationService {

    private final VatDeclarationFactory vatDeclarationFactory;

    private final VatDeclarationRepository vatDeclarationRepository;

    public CreateVatDeclarationService(VatDeclarationFactory vatDeclarationFactory, VatDeclarationRepository vatDeclarationRepository) {
        this.vatDeclarationFactory = vatDeclarationFactory;
        this.vatDeclarationRepository = vatDeclarationRepository;
    }

    public VatDeclaration create(Quarter quarter) {

        VatDeclaration vatDeclaration = vatDeclarationFactory.create(quarter);

        return vatDeclarationRepository.save(vatDeclaration);
    }
}
