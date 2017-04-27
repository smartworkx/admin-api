package nl.smartworkx.admin.adapters.tax.vat.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.DddEntity;

@Entity
@Immutable
public class VatJournalEntry implements DddEntity {
    @Id
    private Long id;

    private Long journalEntryId;

    private VatJournalEntry() {
    }

    VatJournalEntry(Long journalEntryId) {
        this.journalEntryId = journalEntryId;
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

        VatJournalEntry that = (VatJournalEntry) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Long getJournalEntryId() {
        return journalEntryId;
    }
}
