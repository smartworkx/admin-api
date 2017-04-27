package features.glue.shared;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.financialfact.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
@Scope(scopeName = BaseKnowsThe.CUCUMBER_GLUE_SCOPE_NAME)
public class KnowsTheFinancialFact extends BaseKnowsThe<FinancialFact> {

    private FinancialFact.FinancialFactBuilder builder;

    public void setBuilder(FinancialFact.FinancialFactBuilder builder) {
        this.builder = builder;
    }

    public FinancialFact.FinancialFactBuilder getBuilder() {
        return builder;
    }

    public void makeBuilderCurrent() {
        setCurrent(getBuilder().build());
    }
}
