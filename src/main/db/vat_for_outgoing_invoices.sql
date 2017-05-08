select sum(amount) - (sum(amount) * 1/1.21)  from financial_fact where origin_type = 'OUTGOING_INVOICE'

select (sum(amount) * 1/1.21)  from financial_fact where origin_type = 'OUTGOING_INVOICE'