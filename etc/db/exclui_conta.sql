-- Table: exclui_conta

-- DROP TABLE exclui_conta;

CREATE TABLE exclui_conta
(
  codigo character varying(16) NOT NULL,
  email character varying(45),
  status character varying(45),
  CONSTRAINT exclui_conta_pkey PRIMARY KEY (codigo)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE exclui_conta
  OWNER TO postgres;
