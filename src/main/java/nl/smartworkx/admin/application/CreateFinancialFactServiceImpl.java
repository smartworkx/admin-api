package nl.smartworkx.admin.application;

import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Service
public class CreateFinancialFactServiceImpl implements CreateFinancialFactService {
    private final FinancialFactRepository repository;

    public CreateFinancialFactServiceImpl(FinancialFactRepository repository) {
        this.repository = repository;
    }

    @Override
    public FinancialFact create(FinancialFact financialFact) {
        return repository.save(financialFact);
    }
}
