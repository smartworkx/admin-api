package nl.smartworkx.admin;

import static nl.smartworkx.admin.model.TaxCalculator.HIGH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.TaxCalculator;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.JournalEntryTestHelper;
import nl.smartworkx.admin.model.journal.Ledger;
import nl.smartworkx.admin.model.journal.LedgerRepository;
import nl.smartworkx.admin.model.journal.Record;

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

    public JournalEntry createOutgoingInvoiceJournalEntry(Long financialFactId, Amount amountExVat) {

        final Amount vatAmount = amountExVat.calculateExVat(TaxCalculator.HIGH);
        Amount totalAmount = vatAmount.add(amountExVat);
        Ledger deductedVatLedger = ledgerRepository.findByCode("VATS");
        Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
        Ledger toJoriesLedger = ledgerRepository.findByCode("TOJ");
        Record telephoneCostRecord = new Record(toJoriesLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Ledger bankLedger = ledgerRepository.findByCode("DEB");
        Record bankRecord = new Record(bankLedger.getId(), DebitCredit.DEBIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, bankRecord, telephoneCostRecord, deductedVatRecord).build();
        repository.save(journalEntry);
        return journalEntry;

    }

    public JournalEntry createIncomingInvoiceJournalEntry(final Long financialFactId, final double taxRate,
             final String amount) {

        Amount amountExVat = new Amount(amount);
        final Amount vatAmount = amountExVat.calculateExVat(taxRate);
        Amount totalAmount = vatAmount.add(amountExVat);
        // Use record builder
        Record deductedVatRecord = createRecord("DVAT", DebitCredit.DEBIT, vatAmount);
        Record telephoneCostRecord = createRecord("TELC", DebitCredit.CREDIT, amountExVat);
        Record bankRecord = createRecord("BANK", DebitCredit.CREDIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, bankRecord, telephoneCostRecord, deductedVatRecord).build();
        repository.save(journalEntry);
        return journalEntry;
    }

    private Record createRecord(String ledgerCode, DebitCredit debitCredit, Amount amount) {
        Ledger ledger = ledgerRepository.findByCode(ledgerCode);
        return new Record(ledger.getId(), debitCredit, amount);
    }

    public JournalEntry createJournalEntry(FinancialFact financialFact) {
        Ledger deductedVatLedger = ledgerRepository.findByCode("DVAT");
        Ledger telephoneCostsLedger = ledgerRepository.findByCode("TELC");
        Ledger bankLedger = ledgerRepository.findByCode("BANK");
        Amount amountExVat = new Amount("23.00");
        final Amount vatAmount = amountExVat.calculateExVat(HIGH);
        Amount totalAmount = vatAmount.add(amountExVat);
        Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
        Record telephoneCostRecord = new Record(telephoneCostsLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Record bankRecord = new Record(bankLedger.getId(), DebitCredit.CREDIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFact.getId(), deductedVatRecord, telephoneCostRecord, bankRecord).build();

        return repository.save(journalEntry);
    }

}
