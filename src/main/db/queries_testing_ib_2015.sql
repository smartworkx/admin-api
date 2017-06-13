update record set ledger_id = 3 where debit_credit = 'DEBIT' and ledger_id = 2

delete from vat_journal_entry;
delete from vat_declaration;

select sum(amount) from financial_fact where origin_type = 'OUTGOING_INVOICE'

SELECT j.value_date, ff.description, r.amount, r.debit_credit FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'VATS' and j.value_date < '2015-12-31' ORDER BY j.value_date;

SELECT j.value_date, ff.description, r.amount, r.debit_credit FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'VATS' and j.value_date < '2015-12-31' ORDER BY j.value_date;

SELECT r.id, j.value_date, ff.description, r.amount, r.debit_credit, l.name FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code <> 'BANK' and j.value_date < '2015-12-31' and r.debit_credit = 'CREDIT' and r.amount = 50 ORDER BY j.value_date;


select * FROM ledger;

update financial_fact set amount = 6092 where id = 2800;

select sum(vat_serviced_amount_value) from vat_declaration where year = 2015;


select * from financial_fact

select sum(amount) FROM record r
  JOIN ledger l on l.id = r.ledger_id
  JOIN journal_entry j on j.id = r.journal_entry_id
where l.code = 'TOJ' and j.value_date < '2015-12-31'


select * from balance_account

SELECT sum(r.amount) FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'VATS' and j.value_date < '2015-12-31' and r.debit_credit = 'CREDIT';

SELECT sum(r.amount) FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'VATS' and j.value_date < '2015-12-31' and r.debit_credit = 'DEBIT';

SELECT sum(r.amount) FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'BANK' and j.value_date < '2015-12-31' and r.debit_credit = 'CREDIT';

SELECT sum(r.amount) FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'BANK' and j.value_date < '2015-12-31' and r.debit_credit = 'DEBIT';

SELECT sum(r.amount) FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'VATP' and j.value_date < '2015-12-31';

SELECT ff.description, r.amount FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
  JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'COSTS' and j.value_date < '2015-12-31';

select * FROM record WHERE journal_entry_id = 1958
