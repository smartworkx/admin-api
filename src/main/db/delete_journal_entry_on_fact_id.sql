select * FROM financial_fact WHERE description like '%NL86INGB0002445588%'

select * FROM financial_fact where amount = 10.94

SELECT ff.id,r.id, je.id, je.value_date, ff.description, l.name, r.amount FROM journal_entry je
join financial_fact ff on je.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = je.id
  join ledger l on ledger_id = l.id
where financial_fact_id = 3601

delete FROM journal_entry where financial_fact_id =

select id from record r where not exists(select * from journal_entry WHERE id = r.journal_entry_id)
delete from record where id in (select id from record r where not exists(select * from journal_entry WHERE id = r.journal_entry_id))