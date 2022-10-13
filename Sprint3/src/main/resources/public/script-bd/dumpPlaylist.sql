SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: id-playlist; Type: SEQUENCE; Schema: public; Owner: ti2cc
--

CREATE SEQUENCE public."id-playlist"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 1000000
    CACHE 1;


ALTER TABLE public."id-playlist" OWNER TO ti2cc;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: playlist; Type: TABLE; Schema: public; Owner: ti2cc
--

CREATE TABLE public.playlist (
    id integer DEFAULT nextval('public."id-playlist"'::regclass) NOT NULL,
    nome text,
    preco double precision
);


ALTER TABLE public.playlist OWNER TO ti2cc;

--
-- Name: playlist playlist_pkey; Type: CONSTRAINT; Schema: public; Owner: ti2cc
--

ALTER TABLE ONLY public.playlist
    ADD CONSTRAINT playlist_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--   

