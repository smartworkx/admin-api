package nl.smartworkx.admin;

import java.time.LocalDate;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.FinancialFact;
import nl.smartworkx.admin.model.FinancialFactRepository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class RepositoryFinancialFactHelper {

	private FinancialFactRepository repository;

	@Autowired
	public RepositoryFinancialFactHelper(FinancialFactRepository repository) {

		this.repository = repository;
	}

	public FinancialFact createFinancialFact(Money amount, LocalDate date) {

		return repository.save(DomainFinancialFactHelper.create(amount, date));
	}
}
