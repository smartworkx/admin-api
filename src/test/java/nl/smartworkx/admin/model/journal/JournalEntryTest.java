package nl.smartworkx.admin.model.journal;


import static nl.smartworkx.admin.model.journal.JournalEntryTestHelper.createAnonymous;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;

import org.junit.Test;

public class JournalEntryTest {

    @Test
    public void hasRecordMatching() {
        JournalEntry journalEntry = createAnonymous(RecordTestHelper.createAnonymous().ledgerId(1L).build()).build();

        assertThat(journalEntry.hasRecordMatching(newLinkedHashSet(1L))).isTrue();
    }

    @Test
    public void hasOneOfRecordMatching() {
        JournalEntry journalEntry = createAnonymous(RecordTestHelper.createAnonymous().ledgerId(2L).build(), RecordTestHelper.createAnonymous()
                .ledgerId(7L)
                .build()).build();

        assertThat(journalEntry.hasRecordMatching(newLinkedHashSet(1L, 2L))).isTrue();
    }

    @Test
    public void hasNoRecordMatching() {
        JournalEntry journalEntry = createAnonymous(RecordTestHelper.createAnonymous().ledgerId(1L).build()).build();

        assertThat(journalEntry.hasRecordMatching(newLinkedHashSet(2L))).isFalse();
    }
}