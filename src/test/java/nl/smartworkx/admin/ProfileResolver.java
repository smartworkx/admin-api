package nl.smartworkx.admin;

import org.springframework.test.context.ActiveProfilesResolver;
import org.springframework.util.StringUtils;

/**
 * To be able to override the profile on the jenkins server we use this profile.
 *
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class ProfileResolver implements ActiveProfilesResolver {
	@Override public String[] resolve(final Class<?> aClass) {

		final String activeProfiles = System.getProperty("spring.profiles.active");
		return StringUtils.isEmpty(activeProfiles) ? new String[] { "test" } : activeProfiles.split(",");
	}
}
