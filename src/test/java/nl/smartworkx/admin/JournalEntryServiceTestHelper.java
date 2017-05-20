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
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.ledger.LedgerCode;
import nl.smartworkx.admin.model.ledger.LedgerRepository;
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
        Ledger toJorisLedger = ledgerRepository.findByCode("TOJ");
        Record toJorisRecord = new Record(toJorisLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Ledger debtorLedger = ledgerRepository.findByCode("DEB");
        Record debtorRecord = new Record(debtorLedger.getId(), DebitCredit.DEBIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, debtorRecord, toJorisRecord, deductedVatRecord)
                .build();
        repository.save(journalEntry);
        return journalEntry;

    }

    public JournalEntry createIncomingInvoiceJournalEntry(final Long financialFactId, final double taxRate,
            final String amount) {

        Amount amountExVat = new Amount(amount);
        final Amount vatAmount = amountExVat.calculateExVat(taxRate);
        Amount totalAmount = vatAmount.add(amountExVat);
        // Use record builder
        Record deductedVatRecord = createRecord(LedgerCode.DVAT, DebitCredit.DEBIT, vatAmount);
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

    public JournalEntry createCostJournalEntry(FinancialFact financialFact, String amount, String ledgerCode) {
        Ledger deductedVatLedger = ledgerRepository.findByCode(LedgerCode.DVAT);
        Ledger telephoneCostsLedger = ledgerRepository.findByCode(ledgerCode);
        Ledger bankLedger = ledgerRepository.findByCode("CRED");
        Amount amountExVat = new Amount(amount);
        final Amount vatAmount = amountExVat.calculateExVat(HIGH);
        Amount totalAmount = vatAmount.add(amountExVat);
        Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
        Record costRecord = new Record(telephoneCostsLedger.getId(), DebitCredit.CREDIT, amountExVat);
        Record creditRecord = new Record(bankLedger.getId(), DebitCredit.CREDIT, amountExVat.add(totalAmount));

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFact.getId(), deductedVatRecord, costRecord, creditRecord).build();

        return repository.save(journalEntry);
    }

    public JournalEntry createDebtorPaymentJournalEntry(Long financialFactId, Amount amount) {
        Ledger bankLedger = ledgerRepository.findByCode("BANK");
        Ledger debtorLedger = ledgerRepository.findByCode("DEB");
        Record debtorRecord = new Record(debtorLedger.getId(), DebitCredit.CREDIT, amount);
        Record bankRecord = new Record(bankLedger.getId(), DebitCredit.DEBIT, amount);

        JournalEntry journalEntry = JournalEntryTestHelper.createAnonymous(financialFactId, debtorRecord, bankRecord).build();

        return repository.save(journalEntry);
    }


}
