package nl.smartworkx.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Ledger {

	@Id
	@GeneratedValue
	private LedgerId ledgerId;

	private String name;

	public Ledger(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}
}
