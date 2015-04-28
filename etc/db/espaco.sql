-- Table: espaco

-- DROP TABLE espaco;

CREATE TABLE espaco
(
  id bigserial NOT NULL,
  condominio_id bigint,
  descricao character varying(45) NOT NULL,
  antecedencia_maxima_reservar integer,
  antecedencia_minima_reservar integer,
  antecedancia_minima_cancelar integer,
  reservas_quantidade integer,
  reservas_dias integer,
  permitir_posterior boolean,
  permitir_sem_reserva boolean,
  CONSTRAINT espaco_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE espaco
  OWNER TO postgres;
