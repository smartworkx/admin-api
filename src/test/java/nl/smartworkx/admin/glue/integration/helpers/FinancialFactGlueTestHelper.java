package nl.smartworkx.admin.glue.integration.helpers;

import java.time.LocalDate;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.DateUtils;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.glue.shared.BaseKnowsThe;
import nl.smartworkx.admin.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.FinancialFact;

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

    public FinancialFact createFinancialFact(final LocalDate now, final Amount amount) {

        FinancialFact financialFact = testHelper.createFinancialFact(amount, now);
        knowsTheFinancialFact.setCurrent(financialFact);
        return financialFact;
    }


    public FinancialFact createFinancialFact() {
        return createFinancialFact(DateUtils.getNow(), new Amount(10.00));
    }
}
