CREATE TABLE IF NOT EXISTS public.cantiere
(
 id integer NOT NULL DEFAULT
nextval('id_cantiere_seq'::regclass),
 citta character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 via character varying(50) COLLATE pg_catalog."default" NOT
NULL,
 proprietario character varying(30) COLLATE
pg_catalog."default" NOT NULL,
 data_inizio date NOT NULL,
 data_fine date,
 costo integer,
 stato character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 CONSTRAINT cantiere_pkey PRIMARY KEY (id),
 CONSTRAINT cantiere_data_inizio_check CHECK (data_inizio
<= CURRENT_DATE),
 CONSTRAINT cantiere_check CHECK (data_fine > data_inizio),
 CONSTRAINT cantiere_guadagni_check CHECK (costo >= 0),
 CONSTRAINT cantiere_stato_check CHECK (stato::text = ANY
(ARRAY['Aperto'::character varying::text, 'aperto'::character
varying::text, 'Sospeso'::character varying::text,
'sospeso'::character varying::text, 'Finito'::character
varying::text, 'finito'::character varying::text]))
)
TABLESPACE pg_default;