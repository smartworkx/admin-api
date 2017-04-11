package nl.smartworkx.admin;

import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.TaxRate;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.JournalEntryTestHelper;
import nl.smartworkx.admin.model.journal.Ledger;
import nl.smartworkx.admin.model.journal.LedgerRepository;
import nl.smartworkx.admin.model.journal.Record;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class JournalEntryServiceTestHelper {

    private JournalEntryRepository repository;

    private LedgerRepository ledgerRepository;

    @Autowired
    public JournalEntryServiceTestHelper(
            final JournalEntryRepository repository,
            final LedgerRepository ledgerRepository) {

        this.repository = repository;
        this.ledgerRepository = ledgerRepository;
    }

    public JournalEntry createOutgoingInvoiceJournalEntry(Long financialFactId, int taxRate, Amount amountExVat) {

        final MonetaryAmount vatAmount = MonetaryUtil.percent(taxRate).apply(amountExVat.getMoney());
        MonetaryAmount totalAmount = vatAmount.add(amountExVat.getMoney());
        Ledger deductedVatLedger = ledgerRepository.findByCode("VATS");
        Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
        Ledger toJoriesLedger = ledgerRepository.findByCode("TOJ");
        Record telephoneCostRecord = new Record(toJoriesLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Ledger bankLedger = ledgerRepository.findByCode("DEB");
        Record bankRecord = new Record(bankLedger.getId(), DebitCredit.DEBIT, amountExVat.getMoney().add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, bankRecord, telephoneCostRecord, deductedVatRecord).build();
        repository.save(journalEntry);
        return journalEntry;

    }

    public JournalEntry createIncomingInvoiceJournalEntry(final Long financialFactId, final double taxRate,
             final double amount) {

        Money amountExVat = Money.of(amount, "EUR");
        final MonetaryAmount vatAmount = MonetaryUtil.percent(taxRate).apply(amountExVat);
        MonetaryAmount totalAmount = vatAmount.add(amountExVat);
        Record deductedVatRecord = createRecord("DVAT", DebitCredit.DEBIT, vatAmount);
        Record telephoneCostRecord = createRecord("TELC", DebitCredit.CREDIT, amountExVat);
        Record bankRecord = createRecord("BANK", DebitCredit.CREDIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, bankRecord, telephoneCostRecord, deductedVatRecord).build();
        repository.save(journalEntry);
        return journalEntry;
    }

    private Record createRecord(String ledgerCode, DebitCredit debitCredit, MonetaryAmount amount) {
        Ledger ledger = ledgerRepository.findByCode(ledgerCode);
        return new Record(ledger.getId(), debitCredit, amount);
    }

    public JournalEntry createJournalEntry(FinancialFact financialFact) {
        Ledger deductedVatLedger = ledgerRepository.findByCode("DVAT");
        Ledger telephoneCostsLedger = ledgerRepository.findByCode("TELC");
        Ledger bankLedger = ledgerRepository.findByCode("BANK");
        Money amountExVat = Money.of(23.00, "EUR");
        final MonetaryAmount vatAmount = MonetaryUtil.percent(TaxRate.HIGH).apply(amountExVat);
        MonetaryAmount totalAmount = vatAmount.add(amountExVat);
        Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
        Record telephoneCostRecord = new Record(telephoneCostsLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Record bankRecord = new Record(bankLedger.getId(), DebitCredit.CREDIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFact.getId(), deductedVatRecord, telephoneCostRecord, bankRecord).build();

        return repository.save(journalEntry);
    }

}
