package nl.smartworkx.admin;

import java.time.LocalDate;

import org.javamoney.moneta.Money;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
class DomainFinancialFactHelper {
	static FinancialFact create(Amount amount, LocalDate date) {

		return new FinancialFact(date, amount, "bla");
	}
}
