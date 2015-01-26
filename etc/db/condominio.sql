-- Table: condominio

-- DROP TABLE condominio;

CREATE TABLE condominio
(
  id serial NOT NULL,
  nome character varying(60) NOT NULL,
  cnpj character varying(18),
  fone1 character varying(14),
  fone2 character varying(14),
  site character varying(45),
  email character varying(45),
  endereco character varying(45),
  numero character varying(16),
  complemento character varying(45),
  cep character varying(10),
  bairro character varying(45),
  cidade character varying(45),
  sindico character varying(45),
  fone1sindico character varying(14),
  fone2sindico character varying(14),
  emailsindico character varying(45),
  administradora character varying(45),
  fone1administradora character varying(14),
  fone2administradora character varying(14),
  emailadministradora character(45),
  obs character varying(250),
  status character varying(1),
  CONSTRAINT condominio_pkey PRIMARY KEY (id),
  CONSTRAINT condominio_nome_key UNIQUE (nome)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE condominio
  OWNER TO postgres;
