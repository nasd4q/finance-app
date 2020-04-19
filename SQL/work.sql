-- proportion de code dans table quotes, non connus dans table codes

-- # of code unrecognized
SELECT count(distinct code) FROM (
    SELECT * from quotes q1
    LEFT OUTER JOIN codes c1 ON q1.code = trim(leading '0' from c1.sicovam)
    LEFT OUTER JOIN codes c2 ON q1.code = c2.isin
    WHERE c1.sicovam IS NULL AND c2.isin IS NULL
) 


-- total # of code
SELECT count(distinct code) FROM quotes;