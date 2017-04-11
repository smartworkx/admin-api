package nl.smartworkx.admin.glue.integration.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.glue.shared.BaseKnowsThe;
import nl.smartworkx.admin.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.time.DateUtils;

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

    public FinancialFact createFinancialFact(FinancialFact financialFact1) {

        FinancialFact financialFact = testHelper.createFinancialFact(financialFact1);
        knowsTheFinancialFact.setCurrent(financialFact);
        return financialFact;
    }


    public FinancialFact createFinancialFact() {
        return createFinancialFact(FinancialFactTestHelper.create().build());
    }
}
