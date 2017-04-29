SELECT j.value_date, ff.description, r.amount, r.debit_credit FROM journal_entry j
  JOIN financial_fact ff on j.financial_fact_id = ff.id
JOIN record r on r.journal_entry_id = j.id
  JOIN ledger l on r.ledger_id = l.id
where l.code = 'DVAT' ORDER BY j.value_date