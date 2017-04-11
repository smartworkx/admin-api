package nl.smartworkx.admin.glue.integration;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.smartworkx.admin.FinancialFactMvcTestHelper;
import nl.smartworkx.admin.InboxFinancialFactsMvcResult;
import nl.smartworkx.admin.glue.integration.helpers.FinancialFactGlueTestHelper;

/**
 *
 */
public class FinancialFactStepdefs extends AbstractIntegrationStepdefs {

    @Autowired
    private FinancialFactGlueTestHelper testHelper;

    @Autowired
    private FinancialFactMvcTestHelper mvcTestHelper;

    private InboxFinancialFactsMvcResult inboxFinancialFactsMvcResult;

    @Given("^a financial fact$")
    public void aFinancialFact() throws Throwable {
        testHelper.createFinancialFact();
    }

    @When("^the entrepreneur asks for the journalization inbox$")
    public void theEntrepreneurAsksForTheJournalizationInbox() throws Throwable {
         inboxFinancialFactsMvcResult = mvcTestHelper.getInboxFinancialFacts();
    }

    @Then("^the financial fact is shown in the journalization inbox$")
    public void theFinancialFactIsShownInTheJournalizationInbox() throws Throwable {
        inboxFinancialFactsMvcResult.returnsOk(this);
        inboxFinancialFactsMvcResult.arrayHasSizeOf(1);
    }

}