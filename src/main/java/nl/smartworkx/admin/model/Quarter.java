package nl.smartworkx.admin.model;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class Quarter {
	private Integer year;

	private Integer quarter;

	private Quarter() {

	}

	public Quarter(Integer year, Integer quarter) {

		this.year = year;

		this.quarter = quarter;
	}

	public Integer getYear() {

		return year;
	}

	public Integer getQuarter() {

		return quarter;
	}

	public LocalDate getFirstDay() {

		return getFirstMonth().atDay(1);
	}

	private YearMonth getFirstMonth() {

		return YearMonth.of(year, (quarter - 1) * 3 + 1);
	}

	private YearMonth getLastMonth() {

		return Year.of(year).atMonth(quarter * 3);
	}

	public LocalDate getLastDay() {

		return getLastMonth().atEndOfMonth();
	}
}
