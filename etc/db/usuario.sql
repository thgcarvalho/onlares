-- Table: usuario

-- DROP TABLE usuario;

CREATE TABLE usuario
(
  id serial NOT NULL,
  usuario character varying(45) NOT NULL,
  nome character varying(45),
  alertas_por_email boolean,
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT unique_usuario UNIQUE (usuario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usuario
  OWNER TO postgres;
