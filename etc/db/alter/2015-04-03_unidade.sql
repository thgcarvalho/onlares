ALTER TABLE unidade ADD CONSTRAINT unidade_unique UNIQUE (localizacao, condominio_id);
ALTER TABLE unidade DROP COLUMN condominio_id RESTRICT;