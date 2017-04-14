package nl.smartworkx.admin.model.financialfact.inbox;

import java.util.ArrayList;
import java.util.List;

import javax.money.MonetaryAmount;

import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.journal.LedgerRepository;
import nl.smartworkx.admin.model.journal.Record;

/**
 *
 */
public class RecordsBuilder {
    List<Record> records = new ArrayList<>();
    private LedgerRepository ledgerRepository;

    private RecordsBuilder(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public RecordsBuilder add(String ledgerCode, DebitCredit debitCredit, Amount amount) {
        records.add(new Record(ledgerRepository.findByCode(ledgerCode).getId(), debitCredit, amount));
        return this;
    }

    public static RecordsBuilder builder(LedgerRepository ledgerRepository) {
        return new RecordsBuilder(ledgerRepository);
    }

    public List<Record> build() {
        return records;
    }
}
