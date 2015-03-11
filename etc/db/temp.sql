-- Table: temp

-- DROP TABLE temp;

CREATE TABLE temp
(
  id bigserial NOT NULL,
  nome character varying(60),
  conteudo bytea,
  content_type character varying(60),
  data_modificacao date,
  CONSTRAINT temp_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE temp
  OWNER TO postgres;