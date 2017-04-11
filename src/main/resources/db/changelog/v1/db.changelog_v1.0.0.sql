--liquibase formatted sql

--changeset joriswijlens:#1-1 Create table financial fact
CREATE TABLE financial_fact
(
  id           BIGSERIAL PRIMARY KEY NOT NULL,
  currency      VARCHAR(64),
  amount        numeric(19, 2),
  debit_credit INTEGER,
  description  VARCHAR(255),
  value_date   date
);

--changeset joriswijlens:#1-2 Create table financial fact
CREATE TABLE ledger
(
  id   BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

--changeset joriswijlens:#1-3 comment:Journal entry table
CREATE TABLE journal_entry
(
  id                BIGSERIAL PRIMARY KEY NOT NULL,
  description       VARCHAR(255),
  financial_fact_id BIGINT  NOT NULL,
  book_date         date NOT NULL,
  CONSTRAINT fk_financial_fact FOREIGN KEY (financial_fact_id) REFERENCES financial_fact (id)
);

--changeset joriswijlens:#1-4 Record
CREATE TABLE record
(
  id            BIGSERIAL PRIMARY KEY NOT NULL,
  currency      VARCHAR(64),
  amount        numeric(19, 2),
  debit_credit  VARCHAR(64),
  ledger        BIGINT,
  journal_entry BIGINT,
  CONSTRAINT fk_ledger FOREIGN KEY (ledger) REFERENCES ledger (id),
  CONSTRAINT fk_journal_entry FOREIGN KEY (journal_entry) REFERENCES journal_entry (id)
);
--changeset joriswijlens:#1-5 comment:Add index for searching
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-6 comment:Ledger content
ALTER TABLE ledger
  ADD COLUMN code VARCHAR(12);
INSERT INTO ledger (code, name) VALUES ('DEB', 'Debiteuren');
INSERT INTO ledger (code, name) VALUES ('VATS', 'Af te dragen BTW');
INSERT INTO ledger (code, name) VALUES ('DVAT', 'Te vorderen BTW');
INSERT INTO ledger (code, name) VALUES ('CRED', 'Crediteuren');
INSERT INTO ledger (code, name) VALUES ('BANK', 'Bank');
INSERT INTO ledger (code, name) VALUES ('INV', 'Inventaris');
INSERT INTO ledger (code, name) VALUES ('TOJ', 'Omzet Joris');
INSERT INTO ledger (code, name) VALUES ('TOL', 'Omzet Leon');
INSERT INTO ledger (code, name) VALUES ('TOS', 'Omzet gedeeld');
INSERT INTO ledger (code, name) VALUES ('ITC', 'IT kosten');
INSERT INTO ledger (code, name) VALUES ('TRAC', 'Reiskosten');
INSERT INTO ledger (code, name) VALUES ('COSTS', 'Overige kosten');
INSERT INTO ledger (code, name) VALUES ('TELC', 'Telefoon kosten');
INSERT INTO ledger (code, name) VALUES ('EDUC', 'Opleiding');
INSERT INTO ledger (code, name) VALUES ('INSU', 'Verzekering');
INSERT INTO ledger (code, name) VALUES ('LOAN', 'Lening');
INSERT INTO ledger (code, name) VALUES ('INT', 'Rente');


--changeset joriswijlens:#1-7 Bank file uploads
CREATE TABLE bank_file_upload
(
  id                  BIGSERIAL PRIMARY KEY NOT NULL,
  content             TEXT,
  creation_date_time  TIMESTAMP
);

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-8 Add financial fact origin
ALTER TABLE bank_file_upload ADD COLUMN uuid UUID;

ALTER TABLE financial_fact ADD COLUMN origin_uuid UUID;
ALTER TABLE financial_fact ADD COLUMN origin_type VARCHAR(128);
