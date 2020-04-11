
-- List tables in some schemas ...
SELECT table_schema, table_name FROM information_schema.tables
WHERE table_schema='public' or table_schema='data_from_bnains';
