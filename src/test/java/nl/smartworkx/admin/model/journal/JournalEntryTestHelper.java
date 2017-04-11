package nl.smartworkx.admin.model.journal;

import java.time.LocalDate;
import java.util.Arrays;

import nl.smartworkx.admin.model.time.DateUtils;

/**
 *
 */
public class JournalEntryTestHelper {
    public static JournalEntry.JournalEntryBuilder createAnonymous(Long id, Record... records) {
        return JournalEntry.builder().bookDate(DateUtils.today()).financialFactId(id).records(Arrays.asList(records));
    }
}
