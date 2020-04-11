CREATE SCHEMA IF NOT EXISTS data_from_bnains;

-- set this schema as the default one
SET search_path= "$user", data_from_bnains;

CREATE TABLE IF NOT EXISTS members(
   row_id serial PRIMARY KEY,
   code_isin varchar(20),
   code_sicovam varchar(10),
   nom text,
   date date
);