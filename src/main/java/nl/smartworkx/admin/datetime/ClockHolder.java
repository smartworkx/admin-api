package nl.smartworkx.admin.datetime;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Component
public final class ClockHolder {

	private Clock clock = Clock.systemDefaultZone();

	private static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("CET");

	private void setClock(Clock clock) {

		this.clock = clock;
	}

	public Clock getClock() {

		return clock;
	}

	/**
	 * For test purposes only
	 *
	 * @param dateAsString
	 */
	public void setClock(String dateAsString) {

		setClock(Clock.fixed(DateUtils.dateToDateTime(dateAsString).toInstant(ZoneOffset.MIN), DEFAULT_TIME_ZONE));
	}
}
