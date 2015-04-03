nome autorizacao cadastro
Logradouro	 Bairro	 Localidade	 UF	 CEP

 -- Insere
INSERT INTO usuario(localizacao, condominio_id) VALUES (trim('X'), Y);

INSERT INTO usuario(
            email, fone_residencial, fone_comercial, fone_celular, alertas_por_email, 
            nome_completo, tipo, cadastro, logradouro, complemento, cep, bairro, cidade, uf, 
            condominio_id)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);





 -- Relaciona usuario a unidade
UPDATE usuario SET unidade_id = (SELECT id from unidade where condominio_id = 2 and localizacao = '102') WHERE nome_completo = 'email';