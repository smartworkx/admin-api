package nl.smartworkx.admin.model.financialfact;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 */
@Embeddable
public class FinancialFactOrigin {

    @Column(name = "origin_uuid")
    private UUID uuid;

    @Column(name = "origin_type")
    private String type;

    private FinancialFactOrigin() {
    }

    public FinancialFactOrigin(UUID uuid, String type) {
        this.uuid = uuid;
        this.type = type;
    }

    public FinancialFactOrigin(String type) {
        this.uuid = UUID.randomUUID();
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancialFactOrigin)) {
            return false;
        }

        FinancialFactOrigin that = (FinancialFactOrigin) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) {
            return false;
        }
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
