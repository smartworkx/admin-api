CREATE TABLE financial_fact
(
  id           BIGSERIAL PRIMARY KEY NOT NULL,
  amount       VARCHAR(64),
  debit_credit INTEGER,
  description  VARCHAR(255),
  value_date   BYTEA
);


