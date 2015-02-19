-- Table: altera_senha

-- DROP TABLE altera_senha;

CREATE TABLE altera_senha
(
  codigo character varying(16) NOT NULL,
  email character varying(45),
  status character varying(45),
  CONSTRAINT altera_senha_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE altera_senha
  OWNER TO postgres;

