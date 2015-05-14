-- Table: aviso_usuario

-- DROP TABLE aviso_usuario;

CREATE TABLE aviso_usuario
(
  id bigserial NOT NULL,
  aviso_id bigint,
  usuario_id bigint,
  visualizado boolean,
  CONSTRAINT aviso_usuario_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aviso_usuario
  OWNER TO postgres;
