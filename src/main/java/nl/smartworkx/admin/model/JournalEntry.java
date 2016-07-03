package nl.smartworkx.admin.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class JournalEntry implements DddAggregate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_entry")
	@SequenceGenerator(name = "journal_entry", sequenceName = "journal_entry_id_seq")
	private Long id;

	private LocalDate bookDate;

	private Long financialFactId;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Record> records;

	private JournalEntry() {

	}

	public JournalEntry(LocalDate bookDate, FinancialFactId financialFact,
			List<Record> records) {

		this.bookDate = bookDate;

		this.financialFactId = financialFact.getId();
		this.records = records;

	}

	public FinancialFactId getFinancialFact() {

		return new FinancialFactId(id);
	}

	public List<Record> getRecords() {

		return records;
	}

	public LocalDate getBookDate() {

		return bookDate;
	}

	@Override public JournalEntryId getId() {

		return new JournalEntryId(id);
	}
}
