-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  id bigserial NOT NULL,
  email character varying(45),
  nome character varying(60),
  nome_completo character varying(60),
  senha character varying(45),
  profissao character varying(45),
  aniversario character(5),
  fone_residencial character varying(14),
  fone_comercial character varying(14),
  fone_celular character varying(14),
  foto character varying(45),
  autorizacao character varying(45),
  tipo character varying(45),
  cadastro character varying(18),
  logradouro character varying(60),
  complemento character varying(45),
  cep character varying(10),
  bairro character varying(45),
  cidade character varying(45),
  uf character varying(2),
  alertas_por_email boolean,
  condominio_id bigint,
  unidade_id bigint,
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT unique_usuario UNIQUE (email),
  CONSTRAINT fk_condominio FOREIGN KEY (condominio_id)
      REFERENCES condominio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_unidade FOREIGN KEY (unidade_id)
      REFERENCES unidade (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;
