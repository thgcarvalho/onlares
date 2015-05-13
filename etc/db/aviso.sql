-- Table: aviso

-- DROP TABLE aviso;

CREATE TABLE aviso
(
  id bigserial NOT NULL,
  condominio_id bigint,
  titulo character varying(60),
  texto text,
  CONSTRAINT aviso_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aviso
  OWNER TO postgres;
