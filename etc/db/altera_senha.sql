-- Table: altera_senha

-- DROP TABLE altera_senhal;

CREATE TABLE altera_senha
(
  codigo character varying(16) NOT NULL,
  email_antigo character varying(45),
  email_novo character varying(45),
  status character varying(1),
  CONSTRAINT altera_email_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE altera_senha
  OWNER TO postgres;

