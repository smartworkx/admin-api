package nl.smartworkx.admin.interfaces.web.journal;

import static nl.smartworkx.admin.model.MoneyUtils.toMoney;
import static nl.smartworkx.admin.model.time.DateUtils.lenientToDate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.model.financialfact.FinancialFactOrigin;
import nl.smartworkx.admin.model.time.DateUtils;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.financialfact.FinancialFactRepository;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryRepository;
import nl.smartworkx.admin.model.journal.Record;

/**
 * @version 1.0
 * @autror Joris Wijlens
 * @since 1.0
 */
@RestController
@RequestMapping("/journalEntries")
public class JournalizeController {

	private JournalEntryRepository journalEntryRepository;

	private FinancialFactRepository financialFactRepository;

	@Autowired
	public JournalizeController(final JournalEntryRepository journalEntryRepository,
			final FinancialFactRepository financialFactRepository) {

		this.journalEntryRepository = journalEntryRepository;
		this.financialFactRepository = financialFactRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity findById(@RequestBody JournalizeForm form) {

		FinancialFact financialFact = convert(form);
		financialFactRepository.save(financialFact);
		JournalEntry journalEntry = convert(form, financialFact.getId());
		journalEntryRepository.save(journalEntry);
		return ResponseEntity.ok().build();
	}

	private FinancialFact convert(final JournalizeForm form) {

		return new FinancialFact(lenientToDate(form.getValueDate()), toMoney(form.getAmount()), form.getDescription(), new FinancialFactOrigin(form.getType()));
	}

	private JournalEntry convert(final JournalizeForm form, Long financialFactId) {

		List<Record> eur = form.getRecords().stream()
				.map(recordFormLine -> new Record(recordFormLine.getLedger(), recordFormLine.getDebitCredit(),
						toMoney(recordFormLine.getAmount()))).collect(Collectors.toList());
		return new JournalEntry(DateUtils.today(), financialFactId,
				eur);
	}

}
