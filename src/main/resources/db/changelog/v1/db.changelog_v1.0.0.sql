--liquibase formatted sql

--changeset joriswijlens:#1-1 Create table financial fact
CREATE TABLE financial_fact
(
  id           BIGSERIAL PRIMARY KEY NOT NULL,
  currency     VARCHAR(64),
  amount       NUMERIC(19, 2),
  debit_credit INTEGER,
  description  VARCHAR(255),
  value_date   DATE
);

--changeset joriswijlens:#1-2 Create table financial fact
CREATE TABLE ledger
(
  id   BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

--changeset joriswijlens:#1-3 comment:Journal entry table
CREATE TABLE journal_entry
(
  id                BIGSERIAL PRIMARY KEY NOT NULL,
  description       VARCHAR(255),
  financial_fact_id BIGINT                NOT NULL,
  book_date         DATE                  NOT NULL,
  CONSTRAINT fk_financial_fact FOREIGN KEY (financial_fact_id) REFERENCES financial_fact (id)
);

--changeset joriswijlens:#1-4 Record
CREATE TABLE record
(
  id            BIGINT PRIMARY KEY NOT NULL,
  currency      VARCHAR(64),
  amount        NUMERIC(19, 2),
  debit_credit  VARCHAR(64),
  ledger        BIGINT,
  journal_entry BIGINT
);

--changeset joriswijlens:#1-5 comment:Add index for searching
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-6 comment:Ledger content
ALTER TABLE ledger
  ADD COLUMN code VARCHAR(12);
INSERT INTO ledger (id, code, name) VALUES (1, 'DEB', 'Debiteuren');
INSERT INTO ledger (id, code, name) VALUES (2, 'VATS', 'Af te dragen BTW');
INSERT INTO ledger (id, code, name) VALUES (3, 'DVAT', 'Te vorderen BTW');
INSERT INTO ledger (id, code, name) VALUES (4, 'CRED', 'Crediteuren');
INSERT INTO ledger (id, code, name) VALUES (5, 'BANK', 'Bank');
INSERT INTO ledger (id, code, name) VALUES (6, 'INV', 'Inventaris');
INSERT INTO ledger (id, code, name) VALUES (7, 'TOJ', 'Omzet Joris');
INSERT INTO ledger (id, code, name) VALUES (8, 'TOL', 'Omzet Leon');
INSERT INTO ledger (id, code, name) VALUES (9, 'TOS', 'Omzet gedeeld');
INSERT INTO ledger (id, code, name) VALUES (10, 'ITC', 'IT kosten');
INSERT INTO ledger (id, code, name) VALUES (11, 'TRAC', 'Reiskosten');
INSERT INTO ledger (id, code, name) VALUES (12, 'COSTS', 'Overige kosten');
INSERT INTO ledger (id, code, name) VALUES (13, 'TELC', 'Telefoon kosten');
INSERT INTO ledger (id, code, name) VALUES (14, 'EDUC', 'Opleiding');
INSERT INTO ledger (id, code, name) VALUES (15, 'INSU', 'Verzekering');
INSERT INTO ledger (id, code, name) VALUES (16, 'LOAN', 'Lening');
INSERT INTO ledger (id, code, name) VALUES (17, 'INT', 'Rente');

--changeset joriswijlens:#1-7 Bank file uploads
CREATE TABLE bank_file_upload
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  content            TEXT,
  creation_date_time TIMESTAMP
);

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-8 Add financial fact origin
ALTER TABLE bank_file_upload
  ADD COLUMN uuid UUID;

ALTER TABLE financial_fact
  ADD COLUMN origin_uuid UUID;
ALTER TABLE financial_fact
  ADD COLUMN origin_type VARCHAR(128);

--changeset joriswijlens:#1-9 Origin
CREATE TABLE origin
(
  id   BIGSERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(64)
);

INSERT INTO origin (name) VALUES ('OUTGOING_INVOICE');

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-10 Added ledger priv
INSERT INTO ledger (id, code, NAME ) VALUES (18, 'PRIVJ', 'Prive opname Joris');
INSERT INTO ledger (id, code, NAME ) VALUES (19, 'PRIVL', 'Prive opname Leon');

--changeset joriswijlens:#1-11 Added balance
CREATE TABLE balance
(
  id          BIGSERIAL PRIMARY KEY NOT NULL,
  date        DATE,
  description VARCHAR(512)
);

CREATE TABLE balance_account
(
  id              BIGSERIAL PRIMARY KEY NOT NULL,
  ledger          BIGINT,
  balance         BIGINT,
  balance_heading VARCHAR(64),
  amount          NUMERIC(19, 2),
  currency        CHAR(3),
  CONSTRAINT fk_balance_account_ledger FOREIGN KEY (ledger) REFERENCES ledger (id),
  CONSTRAINT fk_balance_account_balance FOREIGN KEY (balance) REFERENCES balance (id)
);



GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

INSERT INTO balance (date,description) VALUES (now(),'test balance');

INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (1, 1, 'CLAIMS', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (2, 1, 'SHORT_RUNNING_DEBT', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (3, 1, 'CLAIMS', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (4, 1, 'SHORT_RUNNING_DEBT', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (5, 1, 'LIQUID_ASSETS', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (6, 1, 'TANGIBLE_FIXED_ASSETS', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (7, 1, 'VENTURE_CAPITAL', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (8, 1, 'VENTURE_CAPITAL', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (9, 1, 'VENTURE_CAPITAL', 0, 'EUR');
INSERT INTO balance_account (ledger, balance, balance_heading, amount, currency) VALUES (16, 1, 'LOAN', 0, 'EUR');
