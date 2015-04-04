ALTER TABLE unidade ADD CONSTRAINT unidade_unique UNIQUE (localizacao, condominio_id);
-- TODO PENDENTE ALTER TABLE usuario DROP COLUMN condominio_id RESTRICT;