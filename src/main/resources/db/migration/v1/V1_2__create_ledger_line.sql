CREATE TABLE ledger_line
(
  id           BIGSERIAL PRIMARY KEY NOT NULL,
  amount       VARCHAR(64),
  debit_credit INTEGER,
  ledger_id    BIGINT,
  CONSTRAINT fk_ledger FOREIGN KEY (ledger_id) REFERENCES ledger (id)
);

