-- Table: foto

-- DROP TABLE foto;

CREATE TABLE foto
(
  id bigserial NOT NULL,
  nome character varying(60),
  conteudo oid,
  content_type character varying(60),
  data_modificacao date,
  CONSTRAINT foto_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE foto
  OWNER TO postgres;
