SELECT
       sum(amount),
       max(value_date),
       min(value_date)
FROM record r
       JOIN ledger l ON l.id = r.ledger_id
       JOIN journal_entry j ON j.id = r.journal_entry_id
WHERE l.code = 'PRIVJ'
  and r.debit_credit = 'DEBIT'
  and j.value_date >= '2018-01-01'
  AND j.value_date <= '2018-12-31'