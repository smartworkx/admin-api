package nl.smartworkx.admin;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@ContextConfiguration
public class IntegrationTestApplicationContext {

	@Resource
	private Environment env;

	@Bean
	public DataSource ownerDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(prop("driver-class-name"));
		dataSource.setUrl(prop("url"));
		dataSource.setUsername(prop("user"));
		dataSource.setPassword(prop("password"));
		return dataSource;
	}

	private String prop(final String s) {

		String prefix = "flyway.";
		return env.getRequiredProperty(prefix + s);
	}
}
