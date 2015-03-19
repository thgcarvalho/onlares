-- Table: veiculo

-- DROP TABLE veiculo;

CREATE TABLE veiculo
(
  id bigserial NOT NULL,
  tipo character varying(45) NOT NULL,
  placa character varying(8) NOT NULL,
  marca character varying(45),
  modelo character varying(45),
  cor character varying(45),
  CONSTRAINT veiculo_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE veiculo
  OWNER TO postgres;
