CREATE TABLE IF NOT EXISTS public.login
(
 username character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 password character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 admin boolean NOT NULL,
 cf_dipendente character(16) COLLATE pg_catalog."default",
 CONSTRAINT login_pkey PRIMARY KEY (username),
 CONSTRAINT login_cf_dipendente_fkey FOREIGN KEY
(cf_dipendente)
 REFERENCES public.dipendente (cf) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION
)
TABLESPACE pg_default;
ALTER TABLE public.login
 OWNER to postgres;