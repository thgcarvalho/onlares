-- Table: anuncio

-- DROP TABLE anuncio;

CREATE TABLE anuncio
(
  id bigserial NOT NULL,
  atividade character varying(45),
  titulo character varying(45),
  descricao character varying(45),
  fone1 character varying(14),
  fone2 character varying(14),
  email character varying(45),
  site character varying(45),
  condominio_id bigint,
  CONSTRAINT anuncio_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE anuncio
  OWNER TO postgres;
