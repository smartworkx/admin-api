package nl.smartworkx.admin.glue.integration;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import nl.smartworkx.admin.DateUtils;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.glue.shared.KnowsTheJournalEntry;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.FinancialFact;
import nl.smartworkx.admin.model.JournalEntry;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalStepdefs extends AbstractIntegrationStepdefs {

	@Autowired
	private FinancialFactServiceTestHelper financialFactServiceTestHelper;

	@Autowired
	private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

	@Autowired
	private KnowsTheFinancialFact knowsTheFinancialFact;

	@Autowired
	private KnowsTheJournalEntry knowsTheJournalEntry;

	@And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(BigDecimal amount, int taxRate) throws Throwable {

		LocalDate now = DateUtils.getNow();
		Amount amountExVat = new Amount(amount, "EUR");
		FinancialFact financialFact = createFinancialFact(now, amountExVat);
		JournalEntry journalEntry = journalEntryServiceTestHelper
				.createOutgoingInvoiceJournalEntry(financialFact.getId(), taxRate, now, amountExVat);
		knowsTheJournalEntry.setCurrent(journalEntry);

	}

	@And("^there is a journal entry for an incoming invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAJournalEntryForAnIncomingInvoiceWithAnAmountOfExVATOf(double amount, int taxRate)
			throws Throwable {

		LocalDate now = DateUtils.getNow();
		FinancialFact financialFact = createFinancialFact(now, new Amount(amount, "EUR"));
		JournalEntry journalEntry = journalEntryServiceTestHelper
				.createIncomingInvoiceJournalEntry(financialFact.getId(), taxRate, now, amount);
		knowsTheJournalEntry.setCurrent(journalEntry);
	}

	private FinancialFact createFinancialFact(final LocalDate now, final Amount amount) {

		FinancialFact financialFact = financialFactServiceTestHelper.createFinancialFact(amount, now);
		knowsTheFinancialFact.setCurrent(financialFact);
		return financialFact;
	}


}
