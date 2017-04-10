package nl.smartworkx.admin.glue.integration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import nl.smartworkx.admin.AdminApplication;
import nl.smartworkx.admin.IntegrationTestApplicationContext;
import nl.smartworkx.admin.ProfileResolver;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AdminApplication.class, IntegrationTestApplicationContext.class },
		loader = SpringBootContextLoader.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(resolver = ProfileResolver.class)
public abstract class AbstractIntegrationStepdefs {

}
