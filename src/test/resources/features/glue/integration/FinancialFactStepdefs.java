package features.glue.integration;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import nl.smartworkx.admin.FinancialFactTestHelper;
import features.glue.integration.helpers.FinancialFactGlueTestHelper;
import features.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFactOrigin;

/**
 *
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class FinancialFactStepdefs extends AbstractIntegrationStepdefs {

    @Autowired
    private FinancialFactGlueTestHelper testHelper;

    @Autowired
    private KnowsTheFinancialFact knowsTheFinancialFact;

    @Given("^a financial fact is created$")
    public void aFinancialFactIsCreated() throws Throwable {
        testHelper.createAFinancialFact();
    }

    @Given("^a financial fact$")
    public void aFinancialFact() throws Throwable {
        knowsTheFinancialFact.setBuilder(FinancialFactTestHelper.create());
    }

    @And("^the financial fact has an origin of \"([^\"]*)\"$")
    public void theFinancialFasHasAnOriginOf(String originType) throws Throwable {
        knowsTheFinancialFact.getBuilder().origin(new FinancialFactOrigin(originType));
    }

    @And("^the financial fact has an amount of (\\d+.\\d+)$")
    public void theFinancialFactHasAnAmountOfExVat(String amount) throws Throwable {
        knowsTheFinancialFact.getBuilder().amount(new Amount(amount));
    }

    @And("^the financial fact is created$")
    public void theFinancialFactIsCreated() throws Throwable {
        testHelper.createTheFinancialFact();
    }

    @And("^the financial fact has a description of \"([^\"]*)\"$")
    public void theFinancialFactHasADescriptionOf(String description) throws Throwable {
        knowsTheFinancialFact.getBuilder().description(description);
    }
}
