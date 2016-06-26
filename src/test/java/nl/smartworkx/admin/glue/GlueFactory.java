package nl.smartworkx.admin.glue;

import org.springframework.context.ApplicationContext;

/**
 * Creates implementations based on cucumber or environment settings as described in the
 * "Cucumber for java" book.
 * Also does lazy initialization of any application context or other "expensive" resources
 * for the features so the fast features that run on pojos are not delayed by the setup of
 * other more expensive tests.
 *
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
final class GlueFactory {
	static <T> T getHelper(Class<T> clazz) {

		return context().getBean(clazz);
	}

	private static ApplicationContext context() {

		return null;
	}
}
