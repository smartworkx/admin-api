package nl.smartworkx.admin.model.profitandlossstatement;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.smartworkx.admin.model.time.DatePeriod;

/**
 * Created by joris on 21-5-17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfitAndLossStatementCreationRequestedEvent {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    private String description;

    public ProfitAndLossStatementCreationRequestedEvent(DatePeriod period, String description) {
        this.startDate = period.getStart();
        this.endDate = period.getEnd();
        this.description = description;
    }

    public DatePeriod getPeriod(){
        return new DatePeriod(startDate, endDate);
    }
}
