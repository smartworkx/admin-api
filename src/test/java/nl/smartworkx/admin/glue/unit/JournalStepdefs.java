package nl.smartworkx.admin.glue.unit;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import nl.smartworkx.admin.RepositoryFinancialFactHelper;
import nl.smartworkx.admin.RepositoryJournalEntryHelper;
import nl.smartworkx.admin.datetime.ClockHolder;
import nl.smartworkx.admin.glue.integration.AbstractIntegrationStepdefs;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalStepdefs extends AbstractIntegrationStepdefs {

	@Autowired
	private RepositoryFinancialFactHelper repositoryFinancialFactHelper;

	@Autowired
	private RepositoryJournalEntryHelper repositoryJournalEntryHelper;

	@And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(BigDecimal amount, int taxRate) throws Throwable {

		LocalDate now = LocalDate.now(ClockHolder.getClock());
		FinancialFact financialFact = repositoryFinancialFactHelper.createFinancialFact(Money.of(amount, "EUR"), now);
		repositoryJournalEntryHelper.createOutgoingInvoiceJournalEntry(financialFact.getId(), taxRate, now);
	}
}
