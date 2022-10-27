CREATE TABLE IF NOT EXISTS public.zona
(
 id integer NOT NULL DEFAULT nextval('id_zona_seq'::regclass),
 grandezza integer NOT NULL,
 tipo character varying(25) COLLATE pg_catalog."default" NOT
NULL,
 id_cantiere integer NOT NULL,
 cf character(16) COLLATE pg_catalog."default",
 CONSTRAINT zona_pkey PRIMARY KEY (id),
 CONSTRAINT zona_cf_fkey FOREIGN KEY (cf)
 REFERENCES public.dipendente (cf) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION,
 CONSTRAINT zona_id_cantiere_fkey FOREIGN KEY (id_cantiere)
 REFERENCES public.cantiere (id) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION,
 CONSTRAINT zona_grandezza_check CHECK (grandezza > 0)
)
TABLESPACE pg_default;
ALTER TABLE public.zona
 OWNER to postgres;