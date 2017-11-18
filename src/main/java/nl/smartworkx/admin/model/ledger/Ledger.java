package nl.smartworkx.admin.model.ledger;

import javax.persistence.*;

import nl.smartworkx.admin.model.balance.BalanceHeadingName;
import nl.smartworkx.admin.model.profitandlossstatement.ProfitAndLossHeadingName;
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

	@Enumerated(EnumType.STRING)
	private ProfitAndLossHeadingName profitAndLossHeading;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	@SuppressWarnings("WeakerAccess")
	public BalanceHeadingName getBalanceHeading() {
		return balanceHeading;
	}

    public boolean shouldShowOnBalance() {
        return getBalanceHeading() != null;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Ledger ledger = (Ledger) o;

		return code.equals(ledger.code);
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	public ProfitAndLossHeadingName getProfitAndLossHeading() {
		return profitAndLossHeading;
	}

	public boolean shouldShowOnProfitAndLossStatement() {
		return getProfitAndLossHeading() != null;
	}

	@Override
	public String toString() {
		return "Ledger{" +
				"code='" + code + '\'' +
				", balanceHeading=" + balanceHeading +
				", profitAndLossHeading=" + profitAndLossHeading +
				'}';
	}
}
