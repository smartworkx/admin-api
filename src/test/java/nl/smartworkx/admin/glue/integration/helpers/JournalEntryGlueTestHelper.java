package nl.smartworkx.admin.glue.integration.helpers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.glue.shared.BaseKnowsThe;
import nl.smartworkx.admin.glue.shared.KnowsTheJournalEntry;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntry;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
@Scope(scopeName = BaseKnowsThe.CUCUMBER_GLUE_SCOPE_NAME)
public class JournalEntryGlueTestHelper {
    private final KnowsTheJournalEntry knowsTheJournalEntry;

    private final JournalEntryServiceTestHelper serviceTestHelper;

    private final FinancialFactGlueTestHelper financialFactGlueTestHelper;

    public JournalEntryGlueTestHelper(KnowsTheJournalEntry knowsTheJournalEntry,
            JournalEntryServiceTestHelper serviceTestHelper,
            FinancialFactGlueTestHelper financialFactGlueTestHelper) {
        this.knowsTheJournalEntry = knowsTheJournalEntry;
        this.serviceTestHelper = serviceTestHelper;
        this.financialFactGlueTestHelper = financialFactGlueTestHelper;
    }

    public void createOutgoingInvoiceJournalEntry(int taxRate, Amount amountExVat) {
        FinancialFact financialFact = financialFactGlueTestHelper.createAFinancialFact(FinancialFactTestHelper.create().amount(amountExVat).build());
        JournalEntry journalEntry = serviceTestHelper
                .createOutgoingInvoiceJournalEntry(financialFact.getId(), taxRate, amountExVat);
        knowsTheJournalEntry.setCurrent(journalEntry);
    }

    public void createIncomingInvoiceJournalEntry(String amount, int taxRate) {
        FinancialFact financialFact = financialFactGlueTestHelper.createAFinancialFact(
                FinancialFactTestHelper.create().amount(new Amount(amount)).build());
        JournalEntry journalEntry = serviceTestHelper
                .createIncomingInvoiceJournalEntry(financialFact.getId(), taxRate, amount);
        knowsTheJournalEntry.setCurrent(journalEntry);
    }

    public void createJournalEntry(FinancialFact current) {
        serviceTestHelper.createJournalEntry(current);
    }
}
