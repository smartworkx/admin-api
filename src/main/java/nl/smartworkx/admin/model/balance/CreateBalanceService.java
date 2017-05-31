package nl.smartworkx.admin.model.balance;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import nl.smartworkx.admin.model.ledger.LedgerRepository;

@Service
@AllArgsConstructor
@Transactional
public class CreateBalanceService {
    private final BalanceRepository balanceRepository;
    BalanceFactory balanceFactory;
    LedgerRepository ledgerRepository;

    public BalanceDetails createBalanceDetails(BalanceCreationRequestedEvent event) {
        Balance balance = balanceFactory.create(event);

        return new BalanceDetails(ledgerRepository, balance);
    }

    public BalanceDetails createBalanceDetails(Long id) {
        return new BalanceDetails(ledgerRepository, balanceRepository.findOne(id));
    }
}