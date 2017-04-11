package nl.smartworkx.admin.interfaces.web.journal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalizeForm {
	private String description;

	private String valueDate;

	private Double amount;

	private List<RecordFormLine> records;

	private String type;

	public Double getAmount() {

		return amount;
	}

	public void setAmount(final Double amount) {

		this.amount = amount;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(final String description) {

		this.description = description;
	}

	public String getValueDate() {

		return valueDate;
	}

	public void setValueDate(final String valueDate) {

		this.valueDate = valueDate;
	}

	@Override public String toString() {

		return "JournalizeForm{" +
				"description='" + description + '\'' +
				", valueDate='" + valueDate + '\'' +
				", amount='" + amount + '\'' +
				", records=" + records +
				'}';
	}

	public List<RecordFormLine> getRecords() {

		return records;
	}

	public void setRecords(final List<RecordFormLine> records) {

		this.records = records;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
