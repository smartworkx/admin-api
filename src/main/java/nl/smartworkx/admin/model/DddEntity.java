package nl.smartworkx.admin.model;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public interface DddEntity {
	<I extends Long> I getId();
}
