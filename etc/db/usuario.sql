-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  id bigserial NOT NULL,
  email character varying(45),
  nome character varying(60),
  senha character varying(45),
  profissao character varying(45),
  aniversario character(5),
  fone_residencial character varying(14),
  fone_comercial character varying(14),
  fone_celular character varying(14),
  foto character varying(45),
  autorizacao character varying(45),
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
