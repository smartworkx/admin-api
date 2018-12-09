SELECT
  j.value_date,
  ff.description,
  ff.origin_type,
l.code,
  r.amount,
  r.debit_credit
FROM journal_entry j
  JOIN financial_fact ff ON j.financial_fact_id = ff.id
  JOIN record r ON r.journal_entry_id = j.id
  JOIN ledger l ON r.ledger_id = l.id
WHERE l.code in ('EVM','PRIVJ','POL','ODR') AND j.value_date = '2016-12-31';


