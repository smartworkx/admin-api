package nl.smartworkx.admin.glue.integration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import nl.smartworkx.admin.AdminApplication;
import nl.smartworkx.admin.IntegrationTestApplicationContext;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AdminApplication.class, IntegrationTestApplicationContext.class },
		loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class AbstractIntegrationStepdefs {

}
