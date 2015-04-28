-- Table: reserva

-- DROP TABLE reserva;

CREATE TABLE reserva
(
  id bigserial NOT NULL,
  unidade_id bigint,
  espaco_id bigint,
  data date,
  hora time without time zone,
  CONSTRAINT reserva_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE reserva
  OWNER TO postgres;
