package nl.smartworkx.admin.model.journal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.experimental.Builder;
import nl.smartworkx.admin.model.DddAggregate;
import nl.smartworkx.admin.model.time.DateUtils;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Entity
@Immutable
public class JournalEntry implements DddAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "journal_entry")
    @SequenceGenerator(name = "journal_entry", sequenceName = "journal_entry_id_seq")
    private Long id;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDateTime;

    private LocalDate bookDate;

    private LocalDate valueDate;

    private Long financialFactId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "journal_entry_id")
    private List<Record> records;

    private JournalEntry() {
        this.creationDateTime = DateUtils.now();
        this.bookDate = DateUtils.today();
    }

    @Builder
    public JournalEntry(final LocalDate valueDate, final Long financialFactId, final List<Record> records) {
        this();
        this.valueDate = valueDate;

        this.financialFactId = financialFactId;
        this.records = records;
    }

    public Long getFinancialFactId() {
        return this.financialFactId;
    }

    public List<Record> getRecords() {
        return records;
    }

    public LocalDate getBookDate() {
        return bookDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JournalEntry that = (JournalEntry) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    boolean hasRecordMatching(Set<Long> ledgerIds) {
        return getRecords()
                .stream()
                .anyMatch(r -> ledgerIds.stream().anyMatch(n -> r.hasLedger(n)));
    }

    public LocalDate getValueDate() {
        return valueDate;
    }
}
