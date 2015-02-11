-- Table: unidade

-- DROP TABLE unidade;

CREATE TABLE unidade
(
  id bigserial NOT NULL,
  localizacao character varying(69),
  CONSTRAINT unidade_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE unidade
  OWNER TO postgres;
