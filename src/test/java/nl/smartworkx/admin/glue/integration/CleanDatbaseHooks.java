package nl.smartworkx.admin.glue.integration;

import java.sql.SQLException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import cucumber.api.java.Before;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class CleanDatbaseHooks {

	private static boolean isInitialized;

	@Autowired
	private DataSource ownerDataSource;

	@Before
	public void cleanStoredProcedure() throws SQLException {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(ownerDataSource);
		if (!isInitialized) {
			long startTime = System.currentTimeMillis();

			jdbcTemplate
					.execute("CREATE OR REPLACE FUNCTION public.truncate_tables() RETURNS VOID AS $$\n"
							+ "DECLARE\n"
							+ "  username VARCHAR := 'admin-owner';\n"
							+ "    statements CURSOR FOR\n"
							+ "    SELECT tablename\n"
							+ "    FROM pg_tables\n"
							+ "    WHERE tableowner = username\n"
							+ "          AND schemaname = 'public'\n"
							+ "          AND tablename NOT IN ('ledger', 'databasechangelog','databasechangeloglock');\n"
							+ "BEGIN\n"
							+ "  FOR stmt IN statements LOOP\n"
							+ "    EXECUTE 'TRUNCATE TABLE ' || quote_ident(stmt.tablename) || ' CASCADE;';\n"
							+ "  END LOOP;\n"
							+ "END;\n"
							+ "$$ LANGUAGE plpgsql VOLATILE;");

			long endTime = System.currentTimeMillis();

			System.out.println("Creating truncate tables took " + (endTime - startTime) + " milliseconds");
			isInitialized = true;
		}
		jdbcTemplate.execute("SELECT truncate_tables()");
	}
}
