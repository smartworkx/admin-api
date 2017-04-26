package nl.smartworkx.admin.model.journal;

import javax.persistence.*;

import nl.smartworkx.admin.model.balance.BalanceHeadingName;
import org.hibernate.annotations.Immutable;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
@Immutable
public class Ledger {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger")
	@SequenceGenerator(name = "ledger", sequenceName = "ledger_id_seq")
	private Long id;

	private String code;

	private String name;

	@Enumerated(EnumType.STRING)
	private BalanceHeadingName balanceHeading;


	public String getName() {

		return name;
	}

	public Long getId() {

		return id;
	}

	public String getCode() {

		return code;
	}


	public BalanceHeadingName getBalanceHeading() {
		return balanceHeading;
	}
}
