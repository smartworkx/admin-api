CREATE TABLE record
(
  id            BIGSERIAL PRIMARY KEY NOT NULL,
  amount        VARCHAR(64),
  debit_credit  VARCHAR(64),
  ledger        BIGINT,
  journal_entry BIGINT,
  CONSTRAINT fk_ledger FOREIGN KEY (ledger) REFERENCES ledger (id),
  CONSTRAINT fk_journal_entry FOREIGN KEY (journal_entry) REFERENCES journal_entry (id)
);

