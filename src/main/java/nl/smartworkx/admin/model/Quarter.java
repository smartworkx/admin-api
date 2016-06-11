package nl.smartworkx.admin.model;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class Quarter {
	private Integer year;
	private Short quarter;

	private Quarter(){}

	public Quarter(Integer year, Short quarter) {

		this.year = year;

		this.quarter = quarter;
	}

	public Integer getYear() {

		return year;
	}

	public Short getQuarter() {

		return quarter;
	}

	public void setYear(Integer year) {

		this.year = year;
	}

	public void setQuarter(Short quarter) {

		this.quarter = quarter;
	}
}
