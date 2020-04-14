CREATE SCHEMA IF NOT EXISTS otherschema;

DROP TABLE IF EXISTS data_from_bnains.quotes;

CREATE TABLE IF NOT EXISTS otherschema.quotesmodified(
id serial PRIMARY KEY,
code VARCHAR(30),
nom VARCHAR(50),
open decimal,
high decimal,
low decimal,
close decimal,
volume integer
);