CREATE TABLE journal_entry_ledger_line
(
  journal_entry_id BIGSERIAL NOT NULL,
  ledger_line_id   BIGINT    NOT NULL,
  CONSTRAINT fk_journal_entry FOREIGN KEY (journal_entry_id) REFERENCES journal_entry (id),
  CONSTRAINT fk_ledger_line FOREIGN KEY (ledger_line_id) REFERENCES ledger_line (id)
);
CREATE UNIQUE INDEX uk_ledger_line ON journal_entry_ledger_line (ledger_line_id);
