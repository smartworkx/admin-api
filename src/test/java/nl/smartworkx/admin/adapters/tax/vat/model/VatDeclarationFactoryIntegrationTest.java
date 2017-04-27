package nl.smartworkx.admin.adapters.tax.vat.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import nl.smartworkx.admin.AbstractIntegrationTest;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.Quarter;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.time.ClockHolder;

public class VatDeclarationFactoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private VatDeclarationFactory vatDeclarationFactory;

    @Autowired
    private FinancialFactServiceTestHelper financialFactServiceTestHelper;

    @Autowired
    private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

    @Test
    public void shouldCreateVatDeclaration() throws Exception {
        ClockHolder.setClock("2017-02-02");

        FinancialFact financialFact1 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(financialFact1.getId(), new Amount("10.00"));
        FinancialFact financialFact2 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(financialFact2.getId(), new Amount("10.00"));

        ClockHolder.resetClock();

        VatDeclaration vatDeclaration   = vatDeclarationFactory.create(new Quarter(2017,1));

        assertThat(vatDeclaration).isNotNull();
        assertThat(vatDeclaration.getVatServicedAmount()).isEqualTo(new Amount("4.20"));
    }

}