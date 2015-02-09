-- Table: unidade

-- DROP TABLE unidade;

CREATE TABLE unidade
(
  id bigserial NOT NULL,
  apartamento character varying(45),
  bloco character varying(45),
  casa character varying(45),
  lote character varying(45),
  quadra character varying(45),
  torre character varying(45),
  CONSTRAINT endereco_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE unidade
  OWNER TO postgres;
