CREATE TABLE IF NOT EXISTS public.sensore
(
 id integer NOT NULL DEFAULT
nextval('id_sensore_seq'::regclass),
 tipo character varying(25) COLLATE pg_catalog."default" NOT
NULL,
 dato double precision,
 soglia double precision NOT NULL,
 posizione character varying(25) COLLATE pg_catalog."default"
NOT NULL,
 allarme boolean NOT NULL,
 cf_dipendente character(16) COLLATE pg_catalog."default" NOT
NULL,
 id_zona integer,
 CONSTRAINT sensore_pkey PRIMARY KEY (id),
 CONSTRAINT sensore_cf_dipendente_fkey FOREIGN KEY
(cf_dipendente)
 REFERENCES public.dipendente (cf) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION,
 CONSTRAINT sensore_id_zona_fkey FOREIGN KEY (id_zona)
 REFERENCES public.zona (id) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION,
 CONSTRAINT sensore_dato_check CHECK (dato >= 0::double
precision)
)
TABLESPACE pg_default;
ALTER TABLE public.sensore
 OWNER to postgres;
