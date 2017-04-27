package features.glue.integration;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import features.glue.integration.helpers.JournalEntryGlueTestHelper;
import features.glue.shared.KnowsTheFinancialFact;
import features.glue.shared.KnowsTheJournalEntry;
import nl.smartworkx.admin.model.Amount;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalStepdefs extends AbstractIntegrationStepdefs {

    @Autowired
    private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

    @Autowired
    private KnowsTheFinancialFact knowsTheFinancialFact;

    @Autowired
    private KnowsTheJournalEntry knowsTheJournalEntry;

    @Autowired
    private JournalEntryGlueTestHelper glueTestHelper;

    @And("^there is a journal entry for an outgoing invoice with an amount of (\\d+) ex VAT$")
    public void thereIsAnOutgoingInvoiceWithAnAmountOfExVATOf(BigDecimal amount, int taxRate) throws Throwable {
        Amount amountExVat = new Amount(amount, "EUR");
        glueTestHelper.createOutgoingInvoiceJournalEntry(taxRate, amountExVat);
    }

    @And("^there is a journal entry for an incoming invoice with an amount of (\\d+) ex VAT of (\\d+)%$")
    public void thereIsAJournalEntryForAnIncomingInvoiceWithAnAmountOfExVATOf(String amount, int taxRate)
            throws Throwable {

        glueTestHelper.createIncomingInvoiceJournalEntry(amount, taxRate);
    }

    @And("^a journal entry is created for the financial fact$")
    public void aJournalEntryIsCreatedForTheFinanincialFact() throws Throwable {
        glueTestHelper.createJournalEntry(knowsTheFinancialFact.getCurrent());
    }
}

