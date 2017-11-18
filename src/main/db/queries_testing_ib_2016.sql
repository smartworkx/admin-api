UPDATE record
SET ledger_id = 3
WHERE debit_credit = 'DEBIT' AND ledger_id = 2

DELETE FROM vat_journal_entry;
DELETE FROM vat_declaration;

SELECT sum(amount)
FROM financial_fact
WHERE origin_type = 'OUTGOING_INVOICE'

SELECT
  j.value_date,
  ff.description,
  r.amount,
  r.debit_credit
FROM journal_entry j
  JOIN financial_fact ff ON j.financial_fact_id = ff.id
  JOIN record r ON r.journal_entry_id = j.id
  JOIN ledger l ON r.ledger_id = l.id
WHERE l.code = 'VATS' AND j.value_date < '2016-12-31'
ORDER BY j.value_date;

SELECT
  j.value_date,
  ff.description,
  r.amount,
  r.debit_credit
FROM journal_entry j
  JOIN financial_fact ff ON j.financial_fact_id = ff.id
  JOIN record r ON r.journal_entry_id = j.id
  JOIN ledger l ON r.ledger_id = l.id
WHERE l.code = 'VATS' AND j.value_date < '2016-12-31'
ORDER BY j.value_date;

SELECT
  r.id,
  j.value_date,
  ff.description,
  r.amount,
  r.debit_credit,
  l.name
FROM journal_entry j
  JOIN financial_fact ff ON j.financial_fact_id = ff.id
  JOIN record r ON r.journal_entry_id = j.id
  JOIN ledger l ON r.ledger_id = l.id
WHERE l.code <> 'BANK' AND j.value_date < '2016-12-31' AND r.debit_credit = 'CREDIT' AND r.amount = 50
ORDER BY j.value_date;

SELECT
sum(r.amount)
FROM journal_entry j
  JOIN financial_fact ff ON j.financial_fact_id = ff.id
  JOIN record r ON r.journal_entry_id = j.id
  JOIN ledger l ON r.ledger_id = l.id
WHERE l.code = 'DEP' AND j.value_date <= '2016-12-31'


SELECT *
FROM ledger;

UPDATE financial_fact
SET amount = 6092
WHERE id = 2800;

SELECT sum(vat_serviced_amount_value)
FROM vat_declaration
WHERE year = 2016;


SELECT
  debit_credit,
  sum(amount)
FROM record r
  JOIN journal_entry j ON j.id = r.journal_entry_id
WHERE j.value_date >= '2016-01-01'
      AND j.value_date <= '2016-12-31'
GROUP BY debit_credit


SELECT sum(amount)
FROM record
WHERE debit_credit = 'DEBIT'

SELECT
  sum(amount),
  max(value_date),
  min(value_date)
FROM record r
  JOIN ledger l ON l.id = r.ledger_id
  JOIN journal_entry j ON j.id = r.journal_entry_id
WHERE l.code = 'PRIVJ'

SELECT
  debit_credit,
  sum(amount),
  count(j.id),
  max(value_date),
  min(value_date)
FROM record r
  JOIN ledger l ON l.id = r.ledger_id
  JOIN journal_entry j ON j.id = r.journal_entry_id
WHERE l.code = 'CRED' and j.value_date >= '2016-01-01'
AND j.value_date <= '2016-12-31'
GROUP BY debit_credit

SELECT
  *
FROM record r
  JOIN ledger l ON l.id = r.ledger_id
  JOIN journal_entry j ON j.id = r.journal_entry_id
WHERE l.code = 'COSTS' and amount = 532.82


SELECT *
FROM ledger
WHERE balance_heading IS NULL AND profit_and_loss_heading IS NULL

select je.id, je.value_date, ff.description, r.debit_credit, l.name, l.code, r.amount from record r
  join journal_entry je on je.id = r.journal_entry_id
  join financial_fact ff on ff.id = je.financial_fact_id
  JOIN ledger l on l.id = r.ledger_id
where ff.value_date = '2016-12-31'

SELECT sum(amount)
FROM record
GROUP BY journal_entry_id
