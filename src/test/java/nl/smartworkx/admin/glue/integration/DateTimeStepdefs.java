package nl.smartworkx.admin.glue.integration;

import cucumber.api.java.en.Given;
import nl.smartworkx.admin.datetime.ClockHolder;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class DateTimeStepdefs {


	@Given("^today is \"([^\"]*)\"$")
	public void todayId(String dateAsString) {

		ClockHolder.setClock(dateAsString);
	}

}
