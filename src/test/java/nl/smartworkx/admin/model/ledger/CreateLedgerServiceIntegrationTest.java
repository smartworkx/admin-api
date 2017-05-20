package nl.smartworkx.admin.model.ledger;

import static nl.smartworkx.admin.model.time.DateUtils.toDate;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import nl.smartworkx.admin.AbstractIntegrationTest;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.time.DatePeriod;
import nl.smartworkx.admin.model.time.DateUtils;
import nl.smartworkx.admin.model.time.Quarter;
import nl.smartworkx.admin.model.balance.Balance;
import nl.smartworkx.admin.model.balance.BalanceAccount;
import nl.smartworkx.admin.model.balance.BalanceFactory;
import nl.smartworkx.admin.model.balance.BalanceRepository;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.time.ClockHolder;

/**
 * Created by joris on 24-4-17.
 */
public class CreateLedgerServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private CreateLedgerService createLedgerService;


    @Autowired
    private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

    @Autowired
    private FinancialFactServiceTestHelper financialFactServiceTestHelper;

    @Autowired
    private LedgerRepository ledgerRepository;


    @Test
    public void createLedger() {

        final String feb2 = "2015-02-02";
        ClockHolder.setClock(feb2);

        LocalDate date = LocalDate.of(2015, 12, 31);
        FinancialFact outgoingInvoice1 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(outgoingInvoice1.getId(), new Amount("10.00"));

        ClockHolder.setClock("2015-02-03");

        FinancialFact outgoingInvoice2 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(outgoingInvoice2.getId(), new Amount("11.00"));

        ClockHolder.setClock("2015-02-04");

        FinancialFact debtorPayment = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createDebtorPaymentJournalEntry(debtorPayment.getId(), new Amount("10.00"));

        ClockHolder.resetClock();

        DatePeriod period = null;
        Ledger ledger = ledgerRepository.findByCode(LedgerCode.VATS);
        LedgerInstance ledgerInstance = createLedgerService.createLedger(ledger,period);

        assertThat(ledgerInstance.getDebitLines().size()).isEqualTo(0);
        assertThat(ledgerInstance.getCreditLines().size()).isEqualTo(2);
        LedgerInstanceLine ledgerInstanceLine1 = ledgerInstance.getCreditLines().get(0);
        assertThat(ledgerInstanceLine1.getDate()).isEqualTo(toDate(feb2));
        assertThat(ledgerInstanceLine1.getDescription()).isEqualTo(outgoingInvoice1.getOrigin());
        assertThat(ledgerInstanceLine1.getAmount()).isEqualTo(new Amount("123"));

    }
}
