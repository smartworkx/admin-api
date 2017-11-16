delete FROM journal_entry WHERE id = (select max(id) from journal_entry);

delete from record where journal_entry_id = (select max(id) from journal_entry);