CREATE TABLE journal_entry
(
  id                BIGSERIAL PRIMARY KEY NOT NULL,
  description       VARCHAR(255),
  financial_fact_id BIGINT,
  CONSTRAINT fk_financial_fact FOREIGN KEY (financial_fact_id) REFERENCES financial_fact (id)
);