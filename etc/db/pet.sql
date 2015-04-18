-- Table: pet

-- DROP TABLE pet;

CREATE TABLE pet
(
  id bigserial NOT NULL,
  tipo character varying(45) NOT NULL,
  raca character varying(45),
  nome character varying(45),
  unidade_id bigint,
  CONSTRAINT pet_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pet
  OWNER TO postgres;
