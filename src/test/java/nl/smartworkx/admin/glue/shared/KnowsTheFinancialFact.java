package nl.smartworkx.admin.glue.shared;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
@Scope(scopeName = KnowsTheFinancialFact.CUCUMBER_GLUE_SCOPE_NAME)
public class KnowsTheFinancialFact extends BaseKnowsThe<FinancialFact> {

}
