package nl.smartworkx.admin.model;

import javax.persistence.Entity;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Ledger extends BaseEntity {

	private String name;

	public Ledger(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}
}
