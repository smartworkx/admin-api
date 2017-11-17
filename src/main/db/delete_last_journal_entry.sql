
delete from record where journal_entry_id = (select max(id) from journal_entry);
delete FROM journal_entry WHERE id = (SELECT  max(id) FROM journal_entry);
