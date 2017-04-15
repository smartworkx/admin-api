package nl.smartworkx.admin.model.financialfact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.Identifiable;

/**
 *
 */
@Entity
@Immutable
public class Origin implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "origin")
    @SequenceGenerator(name = "origin", sequenceName = "origin_id_seq")
    private Long id;

    private String name;

    @Override
    public Object getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
