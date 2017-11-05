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
    private final BalanceFactory balanceFactory;
    private final LedgerRepository ledgerRepository;

    public BalanceDetails createBalanceDetails(BalanceCreationCommand command) {
        Balance balance = balanceFactory.create(command);

        return new BalanceDetails(ledgerRepository, balance);
    }

    public BalanceDetails confirmBalanceDetails(BalanceCreationCommand command) {
        Balance balance = balanceFactory.create(command);

        balanceRepository.save(balance);
        return new BalanceDetails(ledgerRepository, balance);
    }

    public BalanceDetails createBalanceDetails(Long id) {
        return new BalanceDetails(ledgerRepository, balanceRepository.findOne(id));
    }
}