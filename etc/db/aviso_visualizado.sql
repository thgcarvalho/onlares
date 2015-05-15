-- Table: aviso_visualizado

-- DROP TABLE aviso_visualizado;

CREATE TABLE aviso_visualizado
(
  id bigserial NOT NULL,
  aviso_id bigint,
  usuario_id bigint,
  CONSTRAINT aviso_visualizado_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE aviso_visualizado
  OWNER TO postgres;
