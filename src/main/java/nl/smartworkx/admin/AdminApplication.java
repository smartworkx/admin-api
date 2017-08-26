package nl.smartworkx.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import nl.smartworkx.admin.adapters.ing.BankFileUploadEventHandler;
import nl.smartworkx.admin.adapters.tax.vat.model.VatDeclaration;
import nl.smartworkx.admin.model.balance.Balance;
import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.ledger.Ledger;

@SpringBootApplication
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
public class AdminApplication extends RepositoryRestMvcConfiguration {

    @Value("${cross-origin-addresses}")
    private String crossOriginAddresses;

    @Override
    public RepositoryRestConfiguration config() {
        final RepositoryRestConfiguration config = super.config();
        config.exposeIdsFor(Balance.class,FinancialFact.class, VatDeclaration.class, JournalEntry.class, Ledger.class);
        return config;
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(crossOriginAddresses);
    }

    @Bean
    BankFileUploadEventHandler bankFileUploadEventHandler() {
        return new BankFileUploadEventHandler();
    }

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(AdminApplication.class, args);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }



}
