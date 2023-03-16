--
-- PostgreSQL database cluster dump
--

-- Started on 2023-03-16 12:02:36

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

-- Started on 2023-03-16 12:02:36

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

-- Completed on 2023-03-16 12:02:36

--
-- PostgreSQL database dump complete
--

--
-- Database "javan_usermanagement" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7
-- Dumped by pg_dump version 14.7

-- Started on 2023-03-16 12:02:36

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
-- TOC entry 3328 (class 1262 OID 26045)
-- Name: javan_usermanagement; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE javan_usermanagement WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_Indonesia.1252';


ALTER DATABASE javan_usermanagement OWNER TO postgres;

\connect javan_usermanagement

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
-- TOC entry 210 (class 1259 OID 26053)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    role_id integer NOT NULL,
    name character varying,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 26046)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    email character varying,
    password character varying,
    role_id integer,
    user_role_id integer,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 26146)
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
-- TOC entry 211 (class 1259 OID 26094)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_role (
    user_role_id integer NOT NULL,
    name character varying,
    created_time timestamp without time zone,
    last_modified_time timestamp without time zone
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 3320 (class 0 OID 26053)
-- Dependencies: 210
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (role_id, name, created_time, last_modified_time) FROM stdin;
1	ADMIN	2023-03-15 15:44:17	2023-03-15 15:44:17
2	USER	2023-03-15 15:44:17	2023-03-15 15:44:17
\.


--
-- TOC entry 3319 (class 0 OID 26046)
-- Dependencies: 209
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (user_id, email, password, role_id, user_role_id, created_time, last_modified_time) FROM stdin;
5	akunadmin5@gmail.com	$2a$10$/X8LfQkjRfnr0XeSitpDNelRRMCTYh2oeOp1uIUIw3sDm6O44g4tK	1	1	2023-03-15 16:32:51.707	2023-03-15 16:32:51.707
\.


--
-- TOC entry 3321 (class 0 OID 26094)
-- Dependencies: 211
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_role (user_role_id, name, created_time, last_modified_time) FROM stdin;
1	ADMIN	2023-03-15 15:44:17	2023-03-15 15:44:17
2	MAKER	2023-03-15 15:44:17	2023-03-15 15:44:17
3	CHECKER	2023-03-15 15:44:17	2023-03-15 15:44:17
4	APPROVER	2023-03-15 15:44:17	2023-03-15 15:44:17
\.


--
-- TOC entry 3329 (class 0 OID 0)
-- Dependencies: 212
-- Name: user_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_user_seq', 25, true);


--
-- TOC entry 3175 (class 2606 OID 26059)
-- Name: role role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pk PRIMARY KEY (role_id);


--
-- TOC entry 3173 (class 2606 OID 26052)
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (user_id);


--
-- TOC entry 3177 (class 2606 OID 26102)
-- Name: user_role user_role_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pk PRIMARY KEY (user_role_id);


--
-- TOC entry 3179 (class 2606 OID 26074)
-- Name: user user_fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk_role FOREIGN KEY (role_id) REFERENCES public.role(role_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3178 (class 2606 OID 26109)
-- Name: user user_fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_fk_user_role FOREIGN KEY (user_role_id) REFERENCES public.user_role(user_role_id);


-- Completed on 2023-03-16 12:02:36

--
-- PostgreSQL database dump complete
--

-- Completed on 2023-03-16 12:02:36

--
-- PostgreSQL database cluster dump complete
--

