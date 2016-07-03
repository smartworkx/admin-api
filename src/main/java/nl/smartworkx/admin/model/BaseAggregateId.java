package nl.smartworkx.admin.model;

import java.io.Serializable;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class BaseAggregateId implements Serializable {

	private Long id;

	public BaseAggregateId(final Long id) {

		this.id = id;
	}

	public Long getId() {

		return id;
	}
}
