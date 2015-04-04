-- Table: identificador

-- DROP TABLE identificador;

CREATE TABLE identificador
(
  id bigserial NOT NULL,
  usuario_id bigint,
  condominio_id bigint,
  unidade_id bigint,
  CONSTRAINT identificador_pkey PRIMARY KEY (id),
  CONSTRAINT identificador_unique UNIQUE (usuario_id, condominio_id, unidade_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE identificador
  OWNER TO postgres;