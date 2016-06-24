package nl.smartworkx.admin.glue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import cucumber.api.java.en.Given;
import nl.smartworkx.admin.AdminApplication;
import nl.smartworkx.admin.datetime.ClockHolder;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@WebAppConfiguration
@ContextConfiguration(classes = AdminApplication.class, loader = SpringApplicationContextLoader.class)
public class DateTimeStepdefs {

	@Autowired
	private ClockHolder clockHolder;

	@Given("^today is \"([^\"]*)\"$")
	public void todayId(String dateAsString) {

		clockHolder.setClock(dateAsString);
	}

}
