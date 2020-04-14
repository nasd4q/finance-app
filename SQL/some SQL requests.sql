
-- List tables in some schemas ...
SELECT table_schema, table_name FROM information_schema.tables
WHERE table_schema='public' or table_schema='data_from_bnains';

SELECT table_schema, table_name FROM information_schema.tables
WHERE NOT table_schema='information_schema' AND NOT table_schema='pg_catalog';


-- Create schema
CREATE SCHEMA IF NOT EXISTS data_from_bnains;

-- set this schema as the default one
SET search_path= "$user", data_from_bnains;

-- create table
CREATE TABLE IF NOT EXISTS members(
   row_id serial PRIMARY KEY,
   code_isin varchar(20),
   code_sicovam varchar(10),
   nom text,
   date date
);
