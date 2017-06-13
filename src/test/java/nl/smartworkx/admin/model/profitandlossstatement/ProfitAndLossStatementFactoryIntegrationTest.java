package nl.smartworkx.admin.model.profitandlossstatement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import nl.smartworkx.admin.AbstractIntegrationTest;
import nl.smartworkx.admin.FinancialFactServiceTestHelper;
import nl.smartworkx.admin.FinancialFactTestHelper;
import nl.smartworkx.admin.JournalEntryServiceTestHelper;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.time.ClockHolder;
import nl.smartworkx.admin.model.time.DatePeriod;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 * Created by joris on 16-5-17.
 */
public class ProfitAndLossStatementFactoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private ProfitAndLossStatementFactory profitAndLossStatementFactory;

    @Autowired
    private JournalEntryServiceTestHelper journalEntryServiceTestHelper;

    @Autowired
    private FinancialFactServiceTestHelper financialFactServiceTestHelper;

    @Test
    public void createProfitAndLossAccount() {

        final String feb2 = "2015-02-02";
        ClockHolder.setClock(feb2);

        FinancialFact outgoingInvoice1 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(outgoingInvoice1.getId(), new Amount("10.00"));

        ClockHolder.setClock("2015-02-03");

        FinancialFact outgoingInvoice2 = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createOutgoingInvoiceJournalEntry(outgoingInvoice2.getId(), new Amount("11.00"));

        ClockHolder.setClock("2015-02-04");

        FinancialFact debtorPayment = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createDebtorPaymentJournalEntry(debtorPayment.getId(), new Amount("10.00"));

        FinancialFact cost = financialFactServiceTestHelper.createFinancialFact(FinancialFactTestHelper.create().build());
        journalEntryServiceTestHelper.createCostJournalEntry(cost, "14.00", "TELC");

        ClockHolder.resetClock();

        DatePeriod period = new DatePeriod(DateUtils.toDate("2015-01-01"), DateUtils.toDate("2015-12-31"));

        final ProfitAndLossStatement profitAndLossStatement = profitAndLossStatementFactory.create(new ProfitAndLossStatementCreationRequestedEvent
                (period, ""));

        final List<ProfitAndLossHeading> headings = profitAndLossStatement.getHeadings();
        assertThat(headings.size()).isEqualTo(3);
        final Optional<ProfitAndLossHeading> turnoverHeading = profitAndLossStatement.getHeading(ProfitAndLossHeadingName.TURNOVER);
        assertThat(turnoverHeading.isPresent()).isEqualTo(true);
        assertThat(turnoverHeading.get().getAmount()).isEqualTo(new Amount("21.00"));
        final Optional<ProfitAndLossHeading> otherCostsHeading = profitAndLossStatement.getHeading(ProfitAndLossHeadingName.OTHER_COSTS);
        assertThat(otherCostsHeading.isPresent()).isEqualTo(true);
        assertThat(otherCostsHeading.get().getAmount()).isEqualTo(new Amount("14.00"));

        assertThat(profitAndLossStatement.getProfit()).isEqualTo(new Amount("7.00"));


    }

}