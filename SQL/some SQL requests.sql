-- connect : psql "dbname=postgres host=localhost user=postgres password=docker port=9432"


-- List tables in some schemas ...
SELECT table_schema, table_name FROM information_schema.tables
WHERE table_schema='public' or table_schema='data_from_bnains';

-- List user created tables
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


-- delete duplicates CAREFUL : WHERE quotes.id = q1.id !!!
DELETE FROM quotes
USING quotes as q1
LEFT OUTER JOIN
(
SELECT min(id) as KeepId, code, nom, open, high, low, close, volume, date
FROM quotes
GROUP BY code, nom, open, high, low, close, volume, date
) as KeepRows
ON q1.id = KeepId
WHERE quotes.id = q1.id AND KeepId IS NULL;

-- see duplicates
SELECT * FROM quotes
LEFT OUTER JOIN
(
SELECT min(id) as KeepId, code, nom, open, high, low, close, volume, date
FROM quotes
GROUP BY code, nom, open, high, low, close, volume, date
) as KeepRows ON quotes.id = KeepId
WHERE KeepId IS NULL;

-- add column
ALTER TABLE data_from_bnains.quotes
ADD COLUMN asset_id integer;

ALTER TABLE data_from_bnains.quotes
ADD CONSTRAINT fk_asset_id FOREIGN KEY (asset_id) REFERENCES finance.assets (id);

-- drop constraint
ALTER TABLE data_from_bnains.quotes
DROP CONSTRAINT fk_asset_id;
