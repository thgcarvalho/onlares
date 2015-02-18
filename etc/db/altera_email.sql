-- Table: altera_email

-- DROP TABLE altera_email;

CREATE TABLE altera_email
(
  codigo character varying(6) NOT NULL,
  email_antigo character varying(45),
  email_novo character varying(45),
  status character varying(1),
  CONSTRAINT altera_email_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE altera_email
  OWNER TO postgres;
