-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  id bigserial NOT NULL,
  alertas_por_email boolean,
  email character varying(45),
  fone1 character varying(14),
  fone2 character varying(14),
  nome character varying(60),
  senha character varying(45),
  tipo character varying(45),
  condominio_id bigint,
  unidade_id bigint,
  CONSTRAINT usuario_pkey PRIMARY KEY (id),
  CONSTRAINT unique_usuario UNIQUE (usuario)
  CONSTRAINT fk_condominio FOREIGN KEY (condominio_id)
      REFERENCES condominio (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;
