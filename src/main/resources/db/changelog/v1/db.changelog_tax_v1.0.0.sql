--liquibase formatted sql

--changeset joriswijlens:#1-1 Create tables for vat declaration
CREATE TABLE vat_declaration
(
  id                           BIGSERIAL PRIMARY KEY NOT NULL,
  creation_date_time           TIMESTAMP             NOT NULL,
  year                         INT                   NOT NULL,
  quarter                      INT                   NOT NULL,
  vat_serviced_amount_currency VARCHAR(64)           NOT NULL,
  vat_serviced_amount_value    NUMERIC(19, 2)        NOT NULL,
  vat_deducted_amount_currency VARCHAR(64)           NOT NULL,
  vat_deducted_amount_value    NUMERIC(19, 2)        NOT NULL
);

CREATE TABLE vat_journal_entry
(
  id               BIGSERIAL PRIMARY KEY NOT NULL,
  journal_entry_id BIGINT
);

GRANT SELECT, INSERT, UPDATE ON ALL TABLES IN SCHEMA public TO "admin-api";
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO "admin-api";

--changeset joriswijlens:#1-2 Vat declaration unique
CREATE UNIQUE INDEX vat_declaration_unique ON vat_declaration (year,quarter)
