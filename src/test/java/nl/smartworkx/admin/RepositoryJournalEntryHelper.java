package nl.smartworkx.admin;

import java.time.LocalDate;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.smartworkx.admin.model.*;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public class RepositoryJournalEntryHelper {

	private JournalEntryRepository repository;

	private LedgerRepository ledgerRepository;

	@Autowired
	public RepositoryJournalEntryHelper(
			final JournalEntryRepository repository,
			final LedgerRepository ledgerRepository) {

		this.repository = repository;
		this.ledgerRepository = ledgerRepository;
	}

	public JournalEntry createOutgoingInvoiceJournalEntry(Long financialFactId, int taxRate,
			LocalDate now, Money amountExVat) {

		final MonetaryAmount vatAmount = MonetaryUtil.percent(taxRate).apply(amountExVat);
		MonetaryAmount totalAmount = vatAmount.add(amountExVat);
		Ledger deductedVatLedger = ledgerRepository.findByCode("VATS");
		Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
		Ledger telephoneCostsLedger = ledgerRepository.findByCode("TOJ");
		Record telephoneCostRecord = new Record(telephoneCostsLedger.getId(), DebitCredit.CREDIT, amountExVat);
		Ledger bankLedger = ledgerRepository.findByCode("DEB");
		Record bankRecord = new Record(bankLedger.getId(), DebitCredit.DEBIT, amountExVat.add(totalAmount));

		JournalEntry journalEntry = new JournalEntry(now, financialFactId, deductedVatRecord, telephoneCostRecord,
				bankRecord);
		repository.save(journalEntry);
		return journalEntry;

	}

	public JournalEntry createIncomingInvoiceJournalEntry(final Long id, final double taxRate,
			final LocalDate now, final double amount) {

		Money amountExVat = Money.of(amount, "EUR");
		final MonetaryAmount vatAmount = MonetaryUtil.percent(taxRate).apply(amountExVat);
		MonetaryAmount totalAmount = vatAmount.add(amountExVat);
		Ledger deductedVatLedger = ledgerRepository.findByCode("DVAT");
		Record deductedVatRecord = new Record(deductedVatLedger.getId(), DebitCredit.DEBIT, vatAmount);
		Ledger telephoneCostsLedger = ledgerRepository.findByCode("TELC");
		Record telephoneCostRecord = new Record(telephoneCostsLedger.getId(), DebitCredit.CREDIT, amountExVat);
		Ledger bankLedger = ledgerRepository.findByCode("BANK");
		Record bankRecord = new Record(bankLedger.getId(), DebitCredit.CREDIT, amountExVat.add(totalAmount));

		JournalEntry journalEntry = new JournalEntry(now, id, deductedVatRecord, telephoneCostRecord, bankRecord);
		repository.save(journalEntry);
		return journalEntry;
	}
}
