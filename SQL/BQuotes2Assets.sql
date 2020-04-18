-- Comment créer un asset à partir de la table data_from_bnains.quotes ?

-- 1. Identifier un ensemble de codes, pour lequel toutes les lignes concernant l'asset
--    ont un code dans cet ensemble.

SELECT code, nom, count(*) FROM data_from_bnains.quotes 
WHERE LOWER(nom) like '% ****** %' -- critère pour sélectionner ce qui concerne l'asset
GROUP BY code, nom
ORDER BY count(*) DESC;

--  -------------> extraire une liste de codes L_codes "contenant" 
--                   toutes les lignes concernées
-- exemple :

WITH L_codes AS (
SELECT code FROM data_from_bnains.quotes 
WHERE LOWER(nom) like '% ****** %' -- critère pour sélectionner ce qui concerne l'asset
GROUP BY code, nom HAVING count(*)>20 -- seulement certains code retenus...
ORDER BY count(*) DESC
)

-- 2. Observer toutes les lignes qui ont un code dans L_codes

SELECT code, nom, count(*) FROM data_from_bnains.quotes 
WHERE code in (SELECT * FROM L_codes)
GROUP BY code, nom;

-- --------------> confirmer que toutes les lignes avec un code dans L_codes sont 
--                   sont dans les lignes concernées

-- 3. Vérifier qu'il y a bien autant de dates différentes que de lignes au total
--       pour cet asset

SELECT count(*) FROM data_from_bnains.quotes
WHERE code in (SELECT * FROM L_codes)

SELECT count(distinct date) FROM data_from_bnains.quotes
WHERE code in (SELECT * FROM L_codes)

-- 4. Créer un asset : générer un id, ajouter description (correspondant au WHERE du 1.)

-- 5. Ajouter la référence dans data_from_bnains.quotes vers asset_id
UPDATE quotes 
SET asset_id = ? -- à modifier
WHERE code in (SELECT * FROM L_codes)

-- 6. Confirmation
SELECT DISTINCT code, nom FROM data_from_bnains.quotes 
WHERE LOWER(nom) like '% ****** %' -- critère du 1
AND NOT (asset_id = )) 
      -------------> normalement il ne reste plus rien d'intéressant

SELECT DISTINCT code, nom FROM data_from_bnains.quotes 
WHERE asset_id = ? -- à modifier

      -------------->  normalement, tout correspond à l'asset




