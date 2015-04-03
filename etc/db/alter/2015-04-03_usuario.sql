ALTER TABLE usuario RENAME COLUMN tipo TO autorizacao;

ALTER TABLE usuario 
	ADD COLUMN nome_completo varchar(60),
	ADD COLUMN tipo varchar(45),
	ADD COLUMN cadastro varchar(18),
	ADD COLUMN logradouro varchar(60),
	ADD COLUMN complemento varchar(45),
	ADD COLUMN cep varchar(10),
	ADD COLUMN bairro varchar(45),
	ADD COLUMN cidade varchar(45),
	ADD COLUMN uf varchar(2);