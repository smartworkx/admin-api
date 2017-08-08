package nl.smartworkx.admin.model.balance;

import static nl.smartworkx.admin.model.journal.RecordTestHelper.createAnonymous;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.journal.Record;

/**
 * Created by joris on 12-6-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BalanceFactoryTest {

    @InjectMocks
    private BalanceFactory balanceFactory;
/**
    @Test
    public void calculateBalanceAccountAmount() {
        final List<Record> records = Arrays.asList(createAnonymous().amount(new Amount("10.00")).debitCredit(DebitCredit.CREDIT).build(),
                createAnonymous().amount(new Amount("10.00")).debitCredit(DebitCredit.DEBIT).build());

        assertThat(balanceFactory.calculateBalanceAmount(DebitCredit.DEBIT, records)).isEqualTo(Amount.ZERO);
    }

    @Test
    public void calculateBalanceAccountAmountDebit() {
        final List<Record> records = Arrays.asList(createAnonymous().amount(new Amount("5.00")).debitCredit(DebitCredit.CREDIT).build(),
                createAnonymous().amount(new Amount("10.00")).debitCredit(DebitCredit.DEBIT).build());

        assertThat(balanceFactory.calculateBalanceAmount(DebitCredit.DEBIT, records)).isEqualTo(new Amount("5.00"));
    }

    @Test
    public void calculateBalanceAccountAmountCredit() {
        final List<Record> records = Arrays.asList(createAnonymous().amount(new Amount("10.00")).debitCredit(DebitCredit.CREDIT).build(),
                createAnonymous().amount(new Amount("5.00")).debitCredit(DebitCredit.DEBIT).build());

        assertThat(balanceFactory.calculateBalanceAmount(DebitCredit.CREDIT, records)).isEqualTo(new Amount("5.00"));
    }
    **/
}