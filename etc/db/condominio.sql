-- Table: condominio

-- DROP TABLE condominio;

CREATE TABLE condominio
(
  id bigserial NOT NULL,
  administradora character varying(45),
  bairro character varying(45),
  cep character varying(10),
  uf character varying(2),
  cidade character varying(45),
  cnpj character varying(18),
  complemento character varying(45),
  email character varying(45),
  emailadministradora character varying(45),
  emailsindico character varying(45),
  endereco character varying(45),
  fone1 character varying(14),
  fone1administradora character varying(14),
  fone1sindico character varying(14),
  fone2 character varying(14),
  fone2administradora character varying(14),
  fone2sindico character varying(14),
  nome character varying(60) NOT NULL,
  numero character varying(16),
  obs character varying(255),
  sindico character varying(45),
  site character varying(45),
  status character varying(1),
  CONSTRAINT condominio_pkey PRIMARY KEY (id),
  CONSTRAINT condominio_nome_key UNIQUE (nome)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE condominio
  OWNER TO postgres;
