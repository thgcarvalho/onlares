 -- Insert
INSERT INTO unidade(localizacao, condominio_id) VALUES (trim('X'), Y);
 -- Remove espaços em branco no inínio
UPDATE unidade SET localizacao = regexp_replace(localizacao, '^\s+', '') WHERE condominio_id = Y;
 -- Remove espaços em branco no final
UPDATE unidade SET localizacao = regexp_replace(localizacao, '\s+$', '') WHERE condominio_id = Y;

-- The regular expression explained:
-- \s .. regular expression class shorthand for [[:space:]]
-- + .. 1 or more consecutive matches
-- ^ .. start of string
-- $ .. end of string

SELECT regexp_replace(localizacao, '^\s+', '') FROM unidade;
SELECT regexp_replace(localizacao, '\s+$', '') FROM unidade;
UPDATE unidade SET localizacao = trim(localizacao) WHERE condominio_id = Y; -- Trim não funciona para os epaços das planilhas.csv


-- DUPLICADAS 117, 203, 230, 302, 406, 411, 524, 531

SELECT * FROM unidade AS u
INNER JOIN identificador AS i
ON (u.id = i.unidade_id)
WHERE i.condominio_id = 3;