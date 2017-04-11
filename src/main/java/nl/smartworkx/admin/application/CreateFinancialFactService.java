package nl.smartworkx.admin.application;

import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface CreateFinancialFactService {
	FinancialFact create(FinancialFact financialFact);
}
