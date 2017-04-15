package nl.smartworkx.admin.interfaces.web.journal;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalizeForm {

	private List<RecordFormLine> records;
	private Long financialFactId;


	@Override public String toString() {

		return "JournalizeForm{" +
				", records=" + records +
				'}';
	}

	public List<RecordFormLine> getRecords() {

		return records;
	}

	public void setRecords(final List<RecordFormLine> records) {

		this.records = records;
	}

	public Long getFinancialFactId() {
		return financialFactId;
	}

	public void setFinancialFactId(Long financialFactId) {
		this.financialFactId = financialFactId;
	}
}
