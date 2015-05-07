-- Table: tipo_de_autorizacao

-- DROP TABLE tipo_de_autorizacao;

CREATE TABLE tipo_de_autorizacao
(
  id bigserial NOT NULL,
  condominio_id bigint,
  descricao character varying(45) NOT NULL,
  CONSTRAINT tipo_de_autorizacao_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipo_de_autorizacao
  OWNER TO postgres;
