package nl.smartworkx.admin.application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Repository
public class RecordDao {
	@PersistenceContext
	private EntityManager entityManager;

}
