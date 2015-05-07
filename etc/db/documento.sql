-- Table: documento

-- DROP TABLE documento;

CREATE TABLE documento
(
  id bigserial NOT NULL,
  condominio_id bigint,
  titulo character varying(45),
  nome character varying(60),
  conteudo bytea,
  content_type character varying(60),
  data_modificacao date,
  CONSTRAINT documento_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE documento
  OWNER TO postgres;
