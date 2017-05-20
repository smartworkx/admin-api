package nl.smartworkx.admin.infrastructure.jpa;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.smartworkx.admin.model.financialfact.FinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntry;
import nl.smartworkx.admin.model.journal.JournalEntryFinancialFact;
import nl.smartworkx.admin.model.journal.JournalEntryRepositoryCustom;
import nl.smartworkx.admin.model.ledger.Ledger;
import nl.smartworkx.admin.model.time.DatePeriod;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
public class JournalEntryRepositoryImpl implements JournalEntryRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<JournalEntryFinancialFact> findJournalEntriesByDate(DatePeriod period) {

        List<Object[]> resultList = entityManager.createQuery("select j, ff from JournalEntry j "
                + "join fetch j.records r, "
                + "FinancialFact ff "
                + "where ff.valueDate >= :first "
                + "and j.valueDate <= :last "
                + "and j.financialFactId = ff.id "
                + "and j.bookDate <= :today")
                .setParameter("first", period.getStart())
                .setParameter("last", period.getEnd())
                .setParameter("today", DateUtils.today())
                .getResultList();
        return resultList.stream().map(o -> new JournalEntryFinancialFact((JournalEntry) o[0], (FinancialFact) o[1])).collect(Collectors.toSet());
    }
}
