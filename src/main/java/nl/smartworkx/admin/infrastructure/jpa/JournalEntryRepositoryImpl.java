package nl.smartworkx.admin.infrastructure.jpa;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.smartworkx.admin.model.journal.JournalEntryRepositoryCustom;
import nl.smartworkx.admin.model.Quarter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalEntryRepositoryImpl implements JournalEntryRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Object[]> findJournalEntriesForVatReport(final Quarter quarter) {

		return entityManager.createNativeQuery("select l.code, r.amount from journal_entry j "
				+ "join record r on r.journal_entry = j.id "
				+ "join ledger l on r.ledger = l.id "
				+ "and j.book_date >= ? "
				+ "and j.book_date <= ? "
				+ "and (l.code = 'VATS' or l.code = 'DVAT')")
				.setParameter(1, Date.valueOf(quarter.getFirstDay()))
				.setParameter(2, Date.valueOf(quarter.getLastDay()))
				.getResultList();
	}
}
