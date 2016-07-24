package nl.smartworkx.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nl.smartworkx.admin.model.Ledger;
import nl.smartworkx.admin.model.LedgerRepository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/ledgers")
public class LedgerController {

	private LedgerRepository ledgerRepository;

	@Autowired
	public LedgerController(final LedgerRepository ledgerRepository) {

		this.ledgerRepository = ledgerRepository;
	}

	@RequestMapping
	public List<Ledger> ledgers() {

		return (List<Ledger>) ledgerRepository.findAll();
	}

}
