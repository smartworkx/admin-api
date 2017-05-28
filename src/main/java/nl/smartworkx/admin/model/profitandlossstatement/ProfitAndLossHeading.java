package nl.smartworkx.admin.model.profitandlossstatement;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Immutable;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DddEntity;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.journal.Record;


/**
 * Created by joris on 16-5-17.
 */
@Entity
@Immutable
public class ProfitAndLossHeading implements DddEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profit_and_loss_heading")
    @SequenceGenerator(name = "profit_and_loss_heading", sequenceName = "profit_and_loss_heading_id_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProfitAndLossHeadingName name;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name="profit_and_loss_heading_record",
            joinColumns = @JoinColumn( name="profit_and_loss_heading_id"),
            inverseJoinColumns = @JoinColumn( name="record_id")
    )
    private List<Record> records;

    public ProfitAndLossHeading(ProfitAndLossHeadingName name, List<Record> records) {
        this.name = name;
        this.records = records;
    }

    @Override
    public Object getId() {
        return this.id;
    }

    public ProfitAndLossHeadingName getName() {
        return name;
    }

    public Amount getAmount(){
        return Record.sum(records);
    }

    public List<Record> getRecords() {
        return records;
    }

    public DebitCredit getDebitCredit(){
        return name.getDebitCredit();
    }
}
