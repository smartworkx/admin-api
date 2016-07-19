CREATE USER "admin-owner"
  PASSWORD 'password';

CREATE USER "admin-api"
  PASSWORD 'password';

CREATE DATABASE admin;

GRANT ALL ON DATABASE admin TO "admin-owner";

ALTER USER "admin-owner" PASSWORD 'xxx'

