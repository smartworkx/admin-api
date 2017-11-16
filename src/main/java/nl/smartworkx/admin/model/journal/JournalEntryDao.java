package nl.smartworkx.admin.model.journal;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import nl.smartworkx.admin.application.journal.JournalEntryFilterCriteria;
import nl.smartworkx.admin.infrastructure.jdbc.PageableQuery;

@Repository
public class JournalEntryDao {

    private JdbcTemplate jdbcTemplate;

    public Page find(JournalEntryFilterCriteria fc, Pageable pageable) {
        return PageableQuery.create(jdbcTemplate, pageable, new BeanPropertyRowMapper())
                .query("select * from JournalEntry je join FinancialFact ff on je.financialFactId = ff.id")
                .where("je.valueDate <= ?", fc.getEndDate())
                .and("je.valueDate >= ?", fc.getStartDate()).execute();
    }
}
