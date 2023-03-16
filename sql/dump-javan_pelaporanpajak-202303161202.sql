--
-- PostgreSQL database cluster dump
--

-- Started on 2023-03-16 12:02:19

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7
-- Dumped by pg_dump version 14.7

-- Started on 2023-03-16 12:02:20

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

-- Completed on 2023-03-16 12:02:20

--
-- PostgreSQL database dump complete
--

--
-- Database "javan_pelaporanpajak" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7
-- Dumped by pg_dump version 14.7

-- Started on 2023-03-16 12:02:20

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

--
-- TOC entry 3330 (class 1262 OID 26114)
-- Name: javan_pelaporanpajak; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE javan_pelaporanpajak WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_Indonesia.1252';


ALTER DATABASE javan_pelaporanpajak OWNER TO postgres;

\connect javan_pelaporanpajak

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

--
-- TOC entry 213 (class 1259 OID 26154)
-- Name: laporan_id_laporan_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.laporan_id_laporan_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.laporan_id_laporan_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 26122)
-- Name: laporan_pajak; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.laporan_pajak (
    laporan_id bigint NOT NULL,
    nomor_resi character varying,
    status_id integer,
    submitted_by bigint,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public.laporan_pajak OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 26129)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    status_id integer NOT NULL,
    name character varying,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26115)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    email character varying,
    user_role character varying,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26147)
-- Name: user_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_user_seq OWNER TO postgres;

--
-- TOC entry 3321 (class 0 OID 26122)
-- Dependencies: 210
-- Data for Name: laporan_pajak; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.laporan_pajak (laporan_id, nomor_resi, status_id, submitted_by, created_time, last_modified_time) FROM stdin;
\.


--
-- TOC entry 3322 (class 0 OID 26129)
-- Dependencies: 211
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.status (status_id, name, created_time, last_modified_time) FROM stdin;
1	In Progress	2023-03-15 15:44:17	2023-03-15 15:44:17
2	Approved	2023-03-15 15:44:17	2023-03-15 15:44:17
3	Rejected	2023-03-15 15:44:17	2023-03-15 15:44:17
\.


--
-- TOC entry 3320 (class 0 OID 26115)
-- Dependencies: 209
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, email, user_role, created_time, last_modified_time) FROM stdin;
\.


--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 213
-- Name: laporan_id_laporan_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.laporan_id_laporan_seq', 1, false);


--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 212
-- Name: user_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_user_seq', 3, true);


--
-- TOC entry 3176 (class 2606 OID 26126)
-- Name: laporan_pajak laporan_pajak_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laporan_pajak
    ADD CONSTRAINT laporan_pajak_pk PRIMARY KEY (laporan_id);


--
-- TOC entry 3178 (class 2606 OID 26133)
-- Name: status status_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pk PRIMARY KEY (status_id);


--
-- TOC entry 3174 (class 2606 OID 26121)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (user_id);


--
-- TOC entry 3179 (class 2606 OID 26136)
-- Name: laporan_pajak laporan_pajak_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laporan_pajak
    ADD CONSTRAINT laporan_pajak_fk FOREIGN KEY (submitted_by) REFERENCES public."user"(user_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3180 (class 2606 OID 26141)
-- Name: laporan_pajak laporan_pajak_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laporan_pajak
    ADD CONSTRAINT laporan_pajak_fk_1 FOREIGN KEY (status_id) REFERENCES public.status(status_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


-- Completed on 2023-03-16 12:02:20

--
-- PostgreSQL database dump complete
--

-- Completed on 2023-03-16 12:02:20

--
-- PostgreSQL database cluster dump complete
--

