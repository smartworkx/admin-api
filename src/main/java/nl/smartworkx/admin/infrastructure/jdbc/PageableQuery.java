package nl.smartworkx.admin.infrastructure.jdbc;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PageableQuery {
    private final JdbcTemplate jdbcTemplate;
    private final Pageable pageable;
    private final RowMapper rowMapper;

    public PageableQuery(JdbcTemplate jdbcTemplate, Pageable pageable, RowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.pageable = pageable;
        this.rowMapper = rowMapper;
    }

    public static PageableQuery create(JdbcTemplate jdbcTemplate, Pageable pageable, RowMapper rowMapper) {
        return new PageableQuery(jdbcTemplate, pageable, rowMapper);
    }

    public PageableQuery query(String firstQueryPart, Object... parameters) {
        return this;
    }

    public PageableQuery where(String filterQueryPart, Object... parameters) {
        return this;
    }

    public PageableQuery and(String filterQueryPart, Object... parameters) {
        return this;
    }

    public Page execute() {
        return null;
    }

    public String getQuery() {
        return null;
    }

    public Object[] getParameters() {
        return new Object[0];
    }

    public static Page createPage(List queryResult) {
        return null;
    }
}
