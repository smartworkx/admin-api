package nl.smartworkx.admin;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.application.CreateFinancialFactService;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class FinancialFactServiceTestHelper {

	private CreateFinancialFactService service;

	@Autowired
	public FinancialFactServiceTestHelper(CreateFinancialFactService repository) {

		this.service = repository;
	}

	public FinancialFact createFinancialFact(Amount amount, LocalDate date) {

		return service.create(DomainFinancialFactHelper.create(amount, date));
	}
}
