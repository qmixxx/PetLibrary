--
-- PostgreSQL database dump
--

\restrict 1uzT3ZpyUfhcaD1hfdxEupSbrfPndG0VCmHYJsSGBtBe0ZZmTfd5wzTHSqwOH11

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

\unrestrict 1uzT3ZpyUfhcaD1hfdxEupSbrfPndG0VCmHYJsSGBtBe0ZZmTfd5wzTHSqwOH11

