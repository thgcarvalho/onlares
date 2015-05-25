-- Table: envio

-- DROP TABLE envio;

CREATE TABLE envio
(
  id bigserial NOT NULL,
  mensagem_id bigint,
  usuario_id bigint,
  visualizado boolean,
  CONSTRAINT envio_pkey PRIMARY KEY (id),
  CONSTRAINT envio_mensagem_id_usuario_id_key UNIQUE (mensagem_id, usuario_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE envio
  OWNER TO postgres;
