CREATE TABLE IF NOT EXISTS public.dipendente
(
 cf character(16) COLLATE pg_catalog."default" NOT NULL,
 nome character varying(25) COLLATE pg_catalog."default"
NOT NULL,
 cognome character varying(25) COLLATE pg_catalog."default"
NOT NULL,
 data_nascita date NOT NULL,
 email character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 telefono character(10) COLLATE pg_catalog."default" NOT
NULL,
 indirizzo character varying(50) COLLATE
pg_catalog."default" NOT NULL,
 stipendio double precision NOT NULL,
 ruolo character varying(30) COLLATE pg_catalog."default"
NOT NULL,
 id_cantiere integer NOT NULL,
 CONSTRAINT dipendente_pkey PRIMARY KEY (cf),
 CONSTRAINT dipendente_id_cantiere_fkey FOREIGN KEY
(id_cantiere)
 REFERENCES public.cantiere (id) MATCH SIMPLE
 ON UPDATE NO ACTION
 ON DELETE NO ACTION,
 CONSTRAINT dipendente_stipendio_check CHECK (stipendio >
0::double precision),
 CONSTRAINT dipendente_ruolo_check CHECK (ruolo::text = ANY
(ARRAY['Capocantiere'::character varying, 'Operaio'::character
varying, 'Operatore'::character varying]::text[])),
 CONSTRAINT dipendente_data_nascita_check CHECK
((date_part('year'::text, CURRENT_DATE) -
date_part('year'::text, data_nascita)) >= 16::double
precision),
 CONSTRAINT dipendente_telefono_check CHECK (telefono ~
'^[0-9]{10}$'::text),
 CONSTRAINT dipendente_email_check CHECK (email::text ~*
'^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'::text)
)
TABLESPACE pg_default;
ALTER TABLE public.dipendente
 OWNER to postgres;