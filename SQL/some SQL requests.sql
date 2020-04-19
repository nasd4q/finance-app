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
'SET search_path= "$user", data_from_bnains', assets;


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


-- a join
WITH TEMP AS (

SELECT code, nom, c1.sicovam sicovam, c2.isin isin, c1.libelle libelle1,
 c2.libelle libelle2, date 
FROM quotes
LEFT OUTER JOIN codes c1 on (
    quotes.code = trim(leading '0' from c1.sicovam)
)
LEFT OUTER JOIN codes c2 on quotes.code = c2.isin
WHERE quotes.date > '2005-12-31' AND quotes.date < '2006-02-01'

)
SELECT code, nom, sicovam, isin, libelle1, libelle2, count(distinct date) count
FROM TEMP
GROUP BY code, nom, sicovam, isin, libelle1, libelle2 
ORDER BY count DESC;

-- define function
CREATE OR REPLACE FUNCTION convert_to_integer(v_input text)
RETURNS INTEGER AS $$
DECLARE v_int_value INTEGER DEFAULT NULL;
BEGIN
    BEGIN
        v_int_value := v_input::INTEGER;
    EXCEPTION WHEN OTHERS THEN
        RETURN NULL;
    END;
RETURN v_int_value;
END;
$$ LANGUAGE plpgsql;

--create view
CREATE VIEW quotes2006 AS
    SELECT *
    FROM quotes
    WHERE date > '2005-01-31' AND date < '2007-01-01';


--
SELECT q1.code, q1.nom 
from quotes as q1 
LEFT OUTER JOIN codes c1 ON q1.code = c1.isin 
WHERE c1.isin IS NULL 
GROUP BY q1.code, q1.nom;

-- list indexes; create index
SELECT tablename, indexname, indexdef
FROM pg_indexes
WHERE schemaname = 'public' OR schemaname = 'data_from_bnains' OR schemaname = 'assets'
ORDER BY tablename, indexname;

CREATE INDEX IX_date ON quotes (date);

--
CREATE VIEW quotes_sample AS 
SELECT * from quotes 
WHERE (date > '1996-12-31' AND date < '1998-01-01') OR
        (date > '2003-12-31' AND date < '2005-01-01');

--