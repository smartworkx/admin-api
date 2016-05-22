package nl.smartworkx.admin.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class JournalEntry extends BaseEntity{
	private String description;

	@ManyToOne
	private FinancialFact financialFact;

	@OneToMany
	private List<LedgerLine> ledgerLine;
}
