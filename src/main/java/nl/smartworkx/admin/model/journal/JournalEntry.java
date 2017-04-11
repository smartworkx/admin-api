package nl.smartworkx.admin.model.journal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

import lombok.experimental.Builder;
import nl.smartworkx.admin.model.DddAggregate;

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
	@JoinColumn(name = "journal_entry")
	private List<Record> records;

	private JournalEntry() {

	}

	@Builder
	public JournalEntry(final LocalDate bookDate, final Long financialFactId, final List<Record> records) {

		this.bookDate = bookDate;

		this.financialFactId = financialFactId;
		this.records = records;
	}

	public Long getFinancialFactId() {

		return this.financialFactId;
	}

	public List<Record> getRecords() {

		return records;
	}

	public LocalDate getBookDate() {

		return bookDate;
	}

	@Override public Long getId() {

		return id;
	}
}