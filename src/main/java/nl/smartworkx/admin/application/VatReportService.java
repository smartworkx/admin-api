package nl.smartworkx.admin.application;

import java.util.List;
import java.util.Locale;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nl.smartworkx.admin.model.JournalEntryRepository;
import nl.smartworkx.admin.model.LedgerCode;
import nl.smartworkx.admin.model.Quarter;

/**
 * @author Joris Wijlens
 * @version 1.0
 * @since 1.0
 */
@Service
public class VatReportService {

    private JournalEntryRepository journalEntryRepository;


    @Autowired
    public VatReportService(final JournalEntryRepository journalEntryRepository) {

        this.journalEntryRepository = journalEntryRepository;
    }

    public VatReport findVatReport(final Quarter period) {

        List<Object[]> entries = journalEntryRepository
                .findJournalEntriesForVatReport(period);

        final String vatServiced = sumOfRecordsWithType(entries, LedgerCode.VATS);
        final String deductedVat = sumOfRecordsWithType(entries, LedgerCode.DVAT);
        return new VatReport(period, vatServiced, deductedVat);
    }

    private String sumOfRecordsWithType(final List<Object[]> entries, final LedgerCode ledgerCode) {

        MonetaryAmountFormat format = MonetaryFormats.getAmountFormat(Locale.GERMAN);
        return null; /*format.format(entries.stream()
                .filter(obj -> obj[0].equals(ledgerCode.name())).map(obj -> amountConverter.convertToEntityAttribute(
						(String) obj[1]))
				.reduce(MonetaryFunctions.sum()).orElse(MoneyUtils.toMoney(0)));*/
    }
}
