package nl.smartworkx.admin.glue.unit;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.model.time.ClockHolder;
import nl.smartworkx.admin.glue.integration.AbstractIntegrationStepdefs;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

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

	@And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(BigDecimal amount, int taxRate) throws Throwable {

		Amount amountExVat = new Amount(amount, "EUR");
		FinancialFact financialFact = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().amount(amountExVat).build());
		journalEntryServiceTestHelper
				.createOutgoingInvoiceJournalEntry(financialFact.getId(), amountExVat);
	}
}
