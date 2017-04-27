package features.glue.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import features.glue.shared.KnowsTheFinancialFact;
import nl.smartworkx.admin.interfaces.web.journal.RecordFormLine;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.financialfact.inbox.JournalEntryProposalService;
import nl.smartworkx.admin.model.journal.LedgerRepository;

/**
 *
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
public class JournalEntryProposalStepdefs extends AbstractIntegrationStepdefs {

    @Autowired
    private JournalEntryProposalService service;

    @Autowired
    private KnowsTheFinancialFact knowsTheFinancialFact;

    @Autowired
    private LedgerRepository ledgerRepository;

    private List<RecordFormLine> records;

    @When("^the system is asked to produce a proposal$")
    public void theSystemIsAskedToProduceAProposal() {
        records = service.createProposedRecords(knowsTheFinancialFact.getCurrent());
    }

    @Then("^the proposal has a record for \"([^\"]*)\" \"([^\"]*)\" of (\\d+.\\d+)$")
    public void afa(String ledger, DebitCredit debitCredit, String amount) {
        assertThat(records).isNotEmpty();
        assertThat(records.stream().filter(record -> ledgerRepository.findByCode(ledger).getId().equals(record.getLedger()) && debitCredit
                .equals(record.getDebitCredit()) && record.getAmount().equals(new Amount(amount))).collect(Collectors.toList()
        )).hasSize(1);
    }
}
