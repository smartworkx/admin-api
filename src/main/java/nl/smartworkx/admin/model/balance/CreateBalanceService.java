package nl.smartworkx.admin.model.balance;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.interfaces.web.balance.BalanceCreationRequestedEvent;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

@Service
@AllArgsConstructor
public class CreateBalanceService {
    BalanceFactory balanceFactory;
    LedgerRepository ledgerRepository;

    @Transactional
    public BalanceDetails createBalanceDetails(BalanceCreationRequestedEvent event) {
        Balance balance = balanceFactory.create(event);

        return new BalanceDetails(ledgerRepository, balance);
    }
}