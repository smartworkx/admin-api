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
