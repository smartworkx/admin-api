package nl.smartworkx.admin.model.journal;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import nl.smartworkx.admin.AbstractIntegrationTest;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.infrastructure.validation.ValidationException;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.ledger.LedgerCode;

/**
 * Created by joris on 1-6-17.
 */
public class CreateJournalEntryServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CreateJournalEntryService service;
    @Autowired
    private FinancialFactServiceTestHelper financialFactServiceTestHelper;

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionWhenRecordsDoNotBalance() {
        final FinancialFact financialFact = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        JournalEntryCreatedEvent event = new JournalEntryCreatedEvent();
        event.setFinancialFactId(financialFact.getId());
        RecordFormLine recordFormLine = new RecordFormLine(LedgerCode.CRED, DebitCredit.CREDIT, new Amount("10.00"));
        event.setRecords(singletonList(recordFormLine));
        service.create(event);
    }

}