package nl.smartworkx.admin.adapters.ing;

import static com.prowidesoftware.swift.model.mt.mt9xx.MT940.parse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.field.Field86;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import nl.smartworkx.admin.application.CreateFinancialFactService;
import nl.smartworkx.admin.model.Amount;
import nl.smartworkx.admin.model.DebitCredit;
import nl.smartworkx.admin.model.FinancialFact;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Service
public class Mt940ImporterService {

    private CreateFinancialFactService createFinancialFactService;

    @Autowired
    public Mt940ImporterService(CreateFinancialFactService createFinancialFactService) {

        this.createFinancialFactService = createFinancialFactService;
    }

    public void importMt940(String stream) {

        MT940 mt940 = parse(stream);
        int line = 1;
        List<Field86> field86s = Lists.reverse(mt940.getField86());
        for (Field61 field61 : Lists.reverse(mt940.getField61())) {
            Field86 field86 = field86s.get(line);
            createFinancialFactService.create(new FinancialFact(getDate(field61.getValueDate()), getAmount(field61.getAmount()), getValueByCodeword(field86), getCreditDebit(field61
                    .getDCMark())));
            line++;
        }
    }

    private String getValueByCodeword(Field86 field86) {
        String value = field86.getValue().replace("\r\n", "");
        String counterParty = getCounterParty(value);
        String counterpartyPrefix = counterParty == null ? "" : counterParty + " ";
        return StringUtils.chomp(counterpartyPrefix + value.substring(value.indexOf("USTD") + 6), "/")
                .replaceAll(",", " ");
    }

    public String getCounterParty(String value) {

        Pattern pattern = Pattern.compile("/CNTP/.+?/.+?/(.+?)/");

        Matcher matcher = pattern.matcher(value);
        return matcher.find() ? matcher.group(1) : null;
    }

    private DebitCredit getCreditDebit(String dcMark) {

        return "D".equals(dcMark) ? DebitCredit.DEBIT : DebitCredit.CREDIT;
    }

    private Amount getAmount(String amount) {

        return new Amount(new BigDecimal(amount.replace(",", ".")), "EUR");

    }

    private LocalDate getDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

}
