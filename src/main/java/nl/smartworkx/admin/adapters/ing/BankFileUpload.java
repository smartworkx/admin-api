package nl.smartworkx.admin.adapters.ing;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;
import nl.smartworkx.admin.datetime.DateUtils;
import nl.smartworkx.admin.model.DddDomainEvent;

/**
 *
 */
@Entity
public class BankFileUpload implements DddDomainEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_file_upload")
    @SequenceGenerator(name = "bank_file_upload", sequenceName = "bank_file_upload_id_seq")
    private Long id;

    private LocalDateTime creationDateTime;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;

    public BankFileUpload(String content) {
        this.creationDateTime = DateUtils.now();
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    @Override
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankFileUpload)) {
            return false;
        }

        BankFileUpload that = (BankFileUpload) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
