-- Table: envio

-- DROP TABLE envio;

CREATE TABLE envio
(
  id bigserial NOT NULL,
  mensagem_id bigint,
  usuario_id bigint,
  visualizado boolean,
  CONSTRAINT envio_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE envio
  OWNER TO postgres;
