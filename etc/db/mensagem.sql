-- Table: mensagem

-- DROP TABLE mensagem;

CREATE TABLE mensagem
(
  id bigserial NOT NULL,
  usuario_id bigint,
  assunto character varying(45),
  texto text,
  data date,
  hora time without time zone,
  CONSTRAINT mensagem_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE mensagem
  OWNER TO postgres;
