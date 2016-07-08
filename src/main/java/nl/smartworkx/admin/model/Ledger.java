package nl.smartworkx.admin.model;

import javax.persistence.*;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
public class Ledger {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger")
	@SequenceGenerator(name = "ledger", sequenceName = "ledger_id_seq")
	private Long id;

	private String code;

	private String name;


	public String getName() {

		return name;
	}

	public Long getId() {

		return id;
	}

	public String getCode() {

		return code;
	}
}
