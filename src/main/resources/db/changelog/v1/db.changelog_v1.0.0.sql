--liquibase formatted sql

--changeset joriswijlens:#1-1 Create table financial fact
CREATE TABLE financial_fact
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  creation_date_time TIMESTAMP             NOT NULL,
  currency           VARCHAR(64),
  amount             NUMERIC(19, 2),
  debit_credit       VARCHAR(16),
  description        VARCHAR(255),
  value_date         DATE
);

--changeset joriswijlens:#1-2 Create table financial fact
CREATE TABLE ledger
(
  id              BIGINT PRIMARY KEY NOT NULL,
  name            VARCHAR(255),
  balance_heading VARCHAR(64)
);

--changeset joriswijlens:#1-3 comment:Journal entry table
CREATE TABLE journal_entry
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  creation_date_time TIMESTAMP             NOT NULL,
  description        VARCHAR(255),
  financial_fact_id  BIGINT                NOT NULL,
  book_date          DATE                  NOT NULL,
  value_date         DATE                  NOT NULL,
  CONSTRAINT fk_financial_fact FOREIGN KEY (financial_fact_id) REFERENCES financial_fact (id)
);

--changeset joriswijlens:#1-4 Record
CREATE TABLE record
(
  id               BIGSERIAL PRIMARY KEY NOT NULL,
  currency         VARCHAR(64),
  amount           NUMERIC(19, 2),
  debit_credit     VARCHAR(64),
  ledger_id        BIGINT,
  journal_entry_id BIGINT
);

--changeset joriswijlens:#1-5 comment:Add index for searching
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-6 comment:Ledger content
ALTER TABLE ledger
  ADD COLUMN code VARCHAR(12);
INSERT INTO ledger (id, code, name, balance_heading) VALUES (1, 'DEB', 'Debiteuren', 'CLAIMS');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (2, 'VATS', 'Te betalen BTW', 'SHORT_RUNNING_DEBT');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (3, 'DVAT', 'Te vorderen BTW', 'CLAIMS');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (4, 'CRED', 'Crediteuren', 'SHORT_RUNNING_DEBT');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (5, 'BANK', 'Bank', 'LIQUID_ASSETS');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (6, 'INV', 'Inventaris', 'TANGIBLE_FIXED_ASSETS');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (7, 'TOJ', 'Omzet Joris', 'VENTURE_CAPITAL');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (8, 'TOL', 'Omzet Leon', 'VENTURE_CAPITAL');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (9, 'TOS', 'Omzet gedeeld', 'VENTURE_CAPITAL');
INSERT INTO ledger (id, code, name) VALUES (10, 'ITC', 'IT kosten');
INSERT INTO ledger (id, code, name) VALUES (11, 'TRAC', 'Reiskosten');
INSERT INTO ledger (id, code, name) VALUES (12, 'COSTS', 'Overige kosten');
INSERT INTO ledger (id, code, name) VALUES (13, 'TELC', 'Telefoon kosten');
INSERT INTO ledger (id, code, name) VALUES (14, 'EDUC', 'Opleiding');
INSERT INTO ledger (id, code, name) VALUES (15, 'INSU', 'Verzekering');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (16, 'LOAN', 'Lening', 'LOAN');
INSERT INTO ledger (id, code, name) VALUES (17, 'INT', 'Rente');
INSERT INTO ledger (id, code, NAME) VALUES (18, 'PRIVJ', 'Prive opname Joris');
INSERT INTO ledger (id, code, NAME) VALUES (19, 'PRIVL', 'Prive opname Leon');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (20, 'VATP', 'Af te dragen BTW', 'SHORT_RUNNING_DEBT');

--changeset joriswijlens:#1-7 Bank file uploads
CREATE TABLE bank_file_upload
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  content            TEXT,
  creation_date_time TIMESTAMP             NOT NULL
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
INSERT INTO origin (name) VALUES ('INCOMING_INVOICE');
INSERT INTO origin (name) VALUES ('VAT_DECLARATION');

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-11 Added balance
CREATE TABLE balance
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  creation_date_time TIMESTAMP             NOT NULL,
  date               DATE,
  description        VARCHAR(512)
);

CREATE TABLE balance_account
(
  id        BIGSERIAL PRIMARY KEY NOT NULL,
  ledger_id BIGINT,
  balance   BIGINT,
  amount    NUMERIC(19, 2),
  currency  CHAR(3),
  CONSTRAINT fk_balance_account_ledger FOREIGN KEY (ledger_id) REFERENCES ledger (id),
  CONSTRAINT fk_balance_account_balance FOREIGN KEY (balance) REFERENCES balance (id)
);


GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

INSERT INTO balance (creation_date_time, date, description) VALUES (CURRENT_TIMESTAMP, '2014-12-31', 'test balance');

INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (1, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (2, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (3, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (4, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (5, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (6, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (7, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (8, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (9, 1, 0, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (16, 1, 0, 'EUR');

--changeset joriswijlens:#1-12 Profit and loss
ALTER TABLE ledger
  ADD COLUMN profit_and_loss_heading VARCHAR(64);

UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'ITC';
UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'TRAC';
UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'COSTS';
UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'TELC';
UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'EDUC';
UPDATE ledger
SET (profit_and_loss_heading) = ('OTHER_COSTS')
WHERE code = 'INSU';
UPDATE ledger
SET (profit_and_loss_heading) = ('INTEREST')
WHERE code = 'INT';
UPDATE ledger
SET (profit_and_loss_heading) = ('TURNOVER')
WHERE code = 'TOJ';
UPDATE ledger
SET (profit_and_loss_heading) = ('TURNOVER')
WHERE code = 'TOL';
UPDATE ledger
SET (profit_and_loss_heading) = ('TURNOVER')
WHERE code = 'TOS';

CREATE TABLE profit_and_loss_statement
(
  id                 BIGSERIAL PRIMARY KEY NOT NULL,
  creation_date_time TIMESTAMP             NOT NULL,
  start_date         DATE,
  end_date           DATE,
  description        VARCHAR(512),
  amount             NUMERIC(19, 2),
  currency           CHAR(3)
);

CREATE TABLE profit_and_loss_heading
(
  id                           BIGSERIAL PRIMARY KEY NOT NULL,
  name                         VARCHAR(64),
  profit_and_loss_statement_id BIGINT,
  CONSTRAINT fk_profit_and_loss_heading FOREIGN KEY (profit_and_loss_statement_id) REFERENCES ledger (id)
);

--changeset joriswijlens:#1-13 Grants
GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-14 Grants

CREATE TABLE profit_and_loss_heading_record
(
  profit_and_loss_heading_id BIGINT NOT NULL,
  record_id                  BIGINT NOT NULL
);

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-15 Update opening balance 2014
UPDATE ledger SET balance_heading = null where id = 7;
DELETE FROM balance_account WHERE id in (7,8,9);
DELETE FROM ledger WHERE id IN (8,9);

INSERT INTO ledger (id, code, name, balance_heading) VALUES (21, 'ODR', 'Oudedagreserve', 'VENTURE_CAPITAL');
INSERT INTO ledger (id, code, name, balance_heading) VALUES (22, 'EVM', 'Eigen vermogen', 'VENTURE_CAPITAL');

UPDATE balance_account set (amount) = (15931.21) WHERE balance = 1 and ledger_id = 1;
UPDATE balance_account set (amount) = (18763.82) WHERE balance = 1 and ledger_id = 5;
UPDATE balance_account set (amount) = (50000) WHERE balance = 1 and ledger_id = 16;
UPDATE balance_account set (amount) = (63818) WHERE balance = 1 and ledger_id = 21;
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (20, 1, 7562.89, 'EUR');
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (22, 1, 13314.14, 'EUR');

--changeset joriswijlens:#1-16 Correct balance vatp
UPDATE balance_account set (amount) = (8974) WHERE balance = 1 and ledger_id = 20;

--changeset joriswijlens:#1-17 Correct balance oude dag reserve
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (21, 1, 63818, 'EUR');

--changeset joriswijlens:#1-18 Correct balance eigen vermogen
UPDATE balance_account set (amount) = (11903.03) WHERE balance = 1 and ledger_id = 22;

--changeset joriswijlens:#1-19 Correct balance vatp and vats
UPDATE balance_account set (amount) = (0) WHERE balance = 1 and ledger_id = 20;
UPDATE balance_account set (amount) = (8974) WHERE balance = 1 and ledger_id = 2;

--changeset joriswijlens:#1-20 Correct balance vats for double counted invoice 2014
UPDATE balance_account set (amount) = (6436) WHERE balance = 1 and ledger_id = 2;

--changeset joriswijlens:#1-21 Adding private joris to vwnture capital on balance
UPDATE ledger set (balance_heading) = ('VENTURE_CAPITAL') WHERE id = 18;

--changeset joriswijlens:#1-22 Adding balance account in start balance for private joris
INSERT INTO balance_account (ledger_id, balance, amount, currency) VALUES (18, 1, 0, 'EUR');


--changeset joriswijlens:#1-23 Correcting eigen vermogen to equal balance
UPDATE balance_account set (amount) = (14441.03) WHERE balance = 1 and ledger_id = 22;

--changeset joriswijlens:#1-24 Book profit and loss to balance
INSERT INTO ledger (id, code, name) VALUES (23, 'POL', 'Winst of verlies');

--changeset joriswijlens:#1-25 Removing private joris to vwnture capital on balance
UPDATE ledger set (balance_heading) = (null) WHERE id = 18;

--changeset joriswijlens:#1-26 Adding private joris to venture capital on balance
UPDATE ledger set (balance_heading) = ('VENTURE_CAPITAL') WHERE id = 18;

--changeset joriswijlens:#1-27 Removing private joris to vwnture capital on balance
UPDATE ledger set (balance_heading) = (null) WHERE id = 18;

--changeset joriswijlens:#1-28 Adding private joris to venture capital on balance
UPDATE ledger set (balance_heading) = ('VENTURE_CAPITAL') WHERE id = 18;

--changeset joriswijlens:#1-29 Adding write off
INSERT INTO ledger (id, code, name, profit_and_loss_heading) VALUES (24, 'DEP', 'Afschrijvingen', 'WRITE_OFF');

--changeset joriswijlens:#1-30 Removing ledger PRIVL
DELETE FROM ledger WHERE code = 'PRIVL';

--changeset joriswijlens:#1-31 Adding write off for debtors
INSERT INTO ledger (id, code, name, profit_and_loss_heading) VALUES (25, 'DEPD', 'Afschrijvingen debiteuren', 'WRITE_OFF');

--changeset joriswijlens:#1-32 Switch labels between VATP and VATS
UPDATE ledger SET name = 'Te betalen BTW' WHERE code = 'VATP';
UPDATE ledger SET name = 'Af te dragen BTW' WHERE code = 'VATS';
