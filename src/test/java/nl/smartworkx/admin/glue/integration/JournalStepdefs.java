package nl.smartworkx.admin.glue.integration;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import nl.smartworkx.admin.RepositoryFinancialFactHelper;
import nl.smartworkx.admin.RepositoryJournalEntryHelper;
import nl.smartworkx.admin.datetime.ClockHolder;
import nl.smartworkx.admin.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.glue.shared.KnowsTheJournalEntry;
import nl.smartworkx.admin.model.FinancialFact;
import nl.smartworkx.admin.model.JournalEntry;

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

	@Autowired
	private KnowsTheFinancialFact knowsTheFinancialFact;

	@Autowired
	private KnowsTheJournalEntry knowsTheJournalEntry;

	@And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(BigDecimal amount, int taxRate) throws Throwable {

		LocalDate now = LocalDate.now(ClockHolder.getClock());
		FinancialFact financialFact = createFinancialFact(now, Money.of(amount, "EUR"));
		JournalEntry journalEntry = repositoryJournalEntryHelper
				.createOutgoingInvoiceJournalEntry(financialFact.getId(), taxRate, now);
		knowsTheJournalEntry.setCurrent(journalEntry);

	}

	@And("^there is a journal entry for an incoming invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAJournalEntryForAnIncomingInvoiceWithAnAmountOfExVATOf(int amount, int taxRate)
			throws Throwable {

		LocalDate now = LocalDate.now(ClockHolder.getClock());
		FinancialFact financialFact = createFinancialFact(now, Money.of(amount, "EUR"));
		JournalEntry journalEntry = repositoryJournalEntryHelper
				.createIncomingInvoiceJournalEntry(financialFact.getId(), taxRate, now);
		knowsTheJournalEntry.setCurrent(journalEntry);
	}

	private FinancialFact createFinancialFact(final LocalDate now, final Money eur) {

		FinancialFact financialFact = repositoryFinancialFactHelper.createFinancialFact(eur, now);
		knowsTheFinancialFact.setCurrent(financialFact);
		return financialFact;
	}
}
