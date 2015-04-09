 -- Insert
INSERT INTO usuario(localizacao, condominio_id) VALUES (trim('X'), Y);

INSERT INTO usuario(
            email, fone_residencial, fone_comercial, fone_celular, alertas_por_email, 
            nome_completo, tipo, cadastro, logradouro, complemento, cep, bairro, cidade, uf, 
            condominio_id)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

--INSERT INTO usuario(nome_completo, logradouro, bairro, cidade, uf, cep, fone_residencial, fone_celular, email, logradouro) VALUES ('


 -- Relaciona usuario a unidade
UPDATE usuario SET unidade_id = (SELECT id from unidade where condominio_id = 2 and localizacao = '102') WHERE nome_completo = 'email';

-- usuarios do condominio
SELECT * FROM usuario AS u
INNER JOIN identificador AS i
ON (u.id = i.usuario_id)
WHERE i.condominio_id = 3;

-- usuario e unidades do condominio
SELECT * FROM usuario AS us
INNER JOIN identificador AS id
ON (us.id = id.usuario_id)
INNER JOIN unidade AS un
ON (un.id = id.unidade_id)
WHERE id.condominio_id = 1;

-- usuario ativos e unidades do condominio
SELECT * FROM usuario AS us
INNER JOIN identificador AS id
ON (us.id = id.usuario_id)
INNER JOIN unidade AS un
ON (un.id = id.unidade_id)
WHERE us.senha <> '' and id.condominio_id = 1;

-- usuario ativos e unidades do condominio
SELECT * FROM usuario AS us
INNER JOIN identificador AS id
ON (us.id = id.usuario_id)
INNER JOIN unidade AS un
ON (un.id = id.unidade_id)
WHERE us.senha <> '' and id.condominio_id = 1;