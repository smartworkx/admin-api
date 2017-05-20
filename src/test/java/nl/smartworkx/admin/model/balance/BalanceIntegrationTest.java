package nl.smartworkx.admin.model.balance;

import nl.smartworkx.admin.AbstractIntegrationTest;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
import nl.smartworkx.admin.model.time.ClockHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by joris on 24-4-17.
 */
public class BalanceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BalanceFactory balanceFactory;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

    @Autowired
    private FinancialFactServiceTestHelper financialFactServiceTestHelper;

    @Autowired
    private LedgerRepository ledgerRepository;


    @Test
    public void findPreviousBalance() {
        LocalDate date = LocalDate.of(2015, 12, 31);
        Balance balance = balanceRepository.findPreviousBalance(date);
        assertThat(balance).isNotNull();
        assertThat(balance.getAccounts()).isNotEmpty();
    }

    @Test
    public void createBalance() {

        ClockHolder.setClock("2015-02-02");

        LocalDate date = LocalDate.of(2015, 12, 31);
        FinancialFact financialFact1 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(financialFact1.getId(), new Amount("10.00"));
        FinancialFact financialFact2 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(financialFact2.getId(), new Amount("10.00"));

        ClockHolder.resetClock();

        Balance balance = balanceFactory.create(date, "bla bla bla");

        Ledger toj = ledgerRepository.findByCode("TOJ");
        BalanceAccount balanceAccount = balance.findBalanceAccountByLedger(toj);

        assertThat(balanceAccount).isNotNull();
        assertThat(balanceAccount.getAmount()).isEqualTo(new Amount("20.00"));
    }
}
