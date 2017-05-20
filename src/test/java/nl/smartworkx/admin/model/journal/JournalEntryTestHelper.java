package nl.smartworkx.admin.model.journal;

import static java.util.Arrays.asList;
import static nl.smartworkx.admin.model.time.DateUtils.today;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class JournalEntryTestHelper {
    public static JournalEntry.JournalEntryBuilder createAnonymous(Long id, Record... records) {
        return createAnonymous(id, asList(records));
    }

    public static JournalEntry.JournalEntryBuilder createAnonymous(Long id, List<Record> records) {
        return JournalEntry.builder().financialFactId(id).records(records).valueDate
                (today());
    }

    public static JournalEntry.JournalEntryBuilder createAnonymous(Record... records) {
        return createAnonymous(1L, records);
    }
}
