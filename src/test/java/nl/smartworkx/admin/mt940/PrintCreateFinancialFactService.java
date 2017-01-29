package nl.smartworkx.admin.mt940;

import nl.smartworkx.admin.CreateFinancialFactService;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
class PrintCreateFinancialFactService implements CreateFinancialFactService {
    @Override
    public void create(FinancialFact financialFact) {

        System.out.println(financialFact.getValueDate()
                .toString() + "," + financialFact.getDescription() + ",," + convertDebitCredit(financialFact
                .getDebitCredit()) + financialFact
                .getAmount()
                .getNumber());
    }

    private String convertDebitCredit(DebitCredit debitCredit) {

        return debitCredit == DebitCredit.CREDIT ? "" : "-";
    }
}
