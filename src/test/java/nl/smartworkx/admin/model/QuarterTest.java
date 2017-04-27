package nl.smartworkx.admin.model;

import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class QuarterTest {
	@Test
	public void getFirstDay() throws Exception {
		assertThat(new Quarter(2016, 2).getFirstDay(), Matchers.equalTo(LocalDate.of(2016, 4, 1)));
	}

	@Test
	public void getFirstDayOfFirstQuarter() throws Exception {
		assertThat(new Quarter(2016, 1).getFirstDay(), Matchers.equalTo(LocalDate.of(2016, 1, 1)));
	}

	@Test
	public void geLastDay() throws Exception {
		assertThat(new Quarter(2016, 2).getLastDay(), Matchers.equalTo(LocalDate.of(2016, 6, 30)));
	}

	@Test
	public void geLastDayOfLastMonth() throws Exception {
		assertThat(new Quarter(2017, 4).getLastDay(), Matchers.equalTo(LocalDate.of(2017, 12, 31)));
	}
}