-- Table: autorizacao

-- DROP TABLE autorizacao;

CREATE TABLE autorizacao
(
  id bigserial NOT NULL,
  unidade_id bigint,
  tipo_de_autorizacao_id bigint,
  data date,
  hora time without time zone,
  CONSTRAINT autorizacao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE autorizacao
  OWNER TO postgres;
