-- proportion de code dans table quotes, non connus dans table codes

    -- # of code unrecognized
SELECT count(distinct code) FROM (
    SELECT * from quotes_sample q1
    LEFT OUTER JOIN codes_plus c1 ON q1.code = c1.trimmedsicovam
    LEFT OUTER JOIN codes c2 ON q1.code = c2.isin
    WHERE c1.sicovam IS NULL AND c2.isin IS NULL
) AS temp

SELECT count(distinct code) FROM (
    SELECT * from quotes_sample q1
    LEFT OUTER JOIN codes c1 ON q1.code = trim(leading '0' from c1.sicovam)
    LEFT OUTER JOIN codes c2 ON q1.code = c2.isin
    WHERE NOT (c1.sicovam IS NULL AND c2.isin IS NULL)
) AS temp

    -- total # of code
SELECT count(distinct code) FROM quotes;

-- add trimmedsicovam on codes
ALTER TABLE codes ADD COLUMN trimmedsicovam text;
UPDATE codes SET trimmedsicovam = trim(leading '0' from sicovam);
CREATE INDEX IX_trimmedsicovam ON codes (trimmedsicovam);

-- set isin in quotes
ALTER TABLE quotes ADD COLUMN isin text;
ALTER TABLE quotes ADD COLUMN libelle text;


UPDATE quotes q1
SET isin = c1.isin, libelle = c1.libelle
FROM codes c1
WHERE q1.code = c1.isin AND c1.id IS NOT NULL AND 
(q1.isin IS NULL AND q1.libelle IS NULL)

UPDATE quotes q1
SET isin = c1.isin, libelle = c1.nom
FROM abcbourse.codes c1
WHERE (q1.isin IS NULL AND q1.libelle IS NULL) AND q1.code = c1.isin;
