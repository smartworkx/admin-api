CREATE database admin;
CREATE USER "admin-owner" WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE admin TO "admin-owner";

CREATE USER "admin-api" WITH PASSWORD 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, USAGE ON ALL TABLES IN SCHEMA TO admin TO "admin-api";
