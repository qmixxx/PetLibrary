--
-- PostgreSQL database dump
--

\restrict 8jrAeYbSVOEF5sgJl0zy8Egao71c8w0eRt0PthwoK4KUbCgT8FCXvrvWHJn7pvJ

-- Dumped from database version 15.14
-- Dumped by pg_dump version 15.14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: book; Type: TABLE; Schema: public; Owner: petuser
--

CREATE TABLE public.book (
    id bigint NOT NULL,
    author character varying(255),
    isbn character varying(255),
    location character varying(255),
    name character varying(255)
);


ALTER TABLE public.book OWNER TO petuser;

--
-- Name: book_sequence; Type: SEQUENCE; Schema: public; Owner: petuser
--

CREATE SEQUENCE public.book_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_sequence OWNER TO petuser;

--
-- Name: borrowed; Type: TABLE; Schema: public; Owner: petuser
--

CREATE TABLE public.borrowed (
    id bigint NOT NULL,
    due_date date,
    isbn character varying(255),
    user_id character varying(255)
);


ALTER TABLE public.borrowed OWNER TO petuser;

--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: petuser
--

COPY public.book (id, author, isbn, location, name) FROM stdin;
1	Backman	12324	R2-P101	Britt Marii
2	Backman	12325	R2-P102	UweB
6	Backman	12324	R2-P101	Britt Marii
7	Backman	12325	R2-P102	UweB
11	Backman	12324	R2-P101	Britt Marii
12	Backman	12325	R2-P102	UweB
\.


--
-- Data for Name: borrowed; Type: TABLE DATA; Schema: public; Owner: petuser
--

COPY public.borrowed (id, due_date, isbn, user_id) FROM stdin;
3	2000-01-25	12324	111
4	2000-01-25	12325	112
5	2000-01-25	12326	113
8	2000-01-25	12324	111
9	2000-01-25	12325	112
10	2000-01-25	12326	113
13	2000-01-25	12324	111
14	2000-01-25	12325	112
15	2000-01-25	12326	113
\.


--
-- Name: book_sequence; Type: SEQUENCE SET; Schema: public; Owner: petuser
--

SELECT pg_catalog.setval('public.book_sequence', 15, true);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: petuser
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: borrowed borrowed_pkey; Type: CONSTRAINT; Schema: public; Owner: petuser
--

ALTER TABLE ONLY public.borrowed
    ADD CONSTRAINT borrowed_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

\unrestrict 8jrAeYbSVOEF5sgJl0zy8Egao71c8w0eRt0PthwoK4KUbCgT8FCXvrvWHJn7pvJ

