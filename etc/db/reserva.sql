-- Table: reserva

-- DROP TABLE reserva;

CREATE TABLE reserva
(
  id bigserial NOT NULL,
  condominio_id bigint,
  descricao character varying(45) NOT NULL,
  antecedencia_maxima_reservar integer,
  antecedencia_minima_reservar integer,
  antecedancia_minima_cancelar integer,
  permitir_reservas_quantidade integer,
  permitir_reservas_dias integer,
  permitir_posterior boolean,
  CONSTRAINT reserva_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE reserva
  OWNER TO postgres;
