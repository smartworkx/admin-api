package features.glue.integration.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import features.glue.shared.BaseKnowsThe;
import features.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
@Scope(scopeName = BaseKnowsThe.CUCUMBER_GLUE_SCOPE_NAME)
public class FinancialFactGlueTestHelper {

    private final FinancialFactServiceTestHelper testHelper;
    private final KnowsTheFinancialFact knowsTheFinancialFact;

    public FinancialFactGlueTestHelper(FinancialFactServiceTestHelper testHelper, KnowsTheFinancialFact knowsTheFinancialFact) {

        this.testHelper = testHelper;
        this.knowsTheFinancialFact = knowsTheFinancialFact;
    }

    public FinancialFact createAFinancialFact(FinancialFact financialFact1) {

        FinancialFact financialFact = testHelper.createFinancialFact(financialFact1);
        knowsTheFinancialFact.setCurrent(financialFact);
        return financialFact;
    }

    public FinancialFact createAFinancialFact() {
        return createAFinancialFact(FinancialFactTestHelper.create().build());
    }

    public void createTheFinancialFact() {
        knowsTheFinancialFact.makeBuilderCurrent();
        testHelper.createFinancialFact(knowsTheFinancialFact.getCurrent());
    }

}
