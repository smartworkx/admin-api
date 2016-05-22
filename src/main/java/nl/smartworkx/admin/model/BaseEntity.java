package nl.smartworkx.admin.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {

		return id;
	}
}
