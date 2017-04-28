package nl.smartworkx.admin.adapters.tax.vat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.DddEntity;

@Entity
@Immutable
public class VatJournalEntry implements DddEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vat_journal_entry")
    @SequenceGenerator(name = "vat_journal_entry", sequenceName = "journal_entry_id_seq")
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

        return journalEntryId.equals(that.journalEntryId);
    }

    @Override
    public int hashCode() {
        return journalEntryId.hashCode();
    }

    public Long getJournalEntryId() {
        return journalEntryId;
    }
}
