package nl.smartworkx.admin.application;

import nl.smartworkx.admin.model.FinancialFact;
import org.springframework.stereotype.Service;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface CreateFinancialFactService {
	FinancialFact create(FinancialFact financialFact);
}
