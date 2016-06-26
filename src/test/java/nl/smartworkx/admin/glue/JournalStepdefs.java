package nl.smartworkx.admin.glue;

import org.javamoney.moneta.Money;
import cucumber.api.java.en.And;
import nl.smartworkx.admin.JournalEntryHelper;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalStepdefs {
	@And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
	public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(Money amount, int taxRate) throws Throwable {
		GlueFactory.getHelper(JournalEntryHelper.class).createJournalEntry(amount, taxRate);
	}
}
