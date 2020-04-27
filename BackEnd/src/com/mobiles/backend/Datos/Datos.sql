--------------------------------------------------------
--  EJECUTAR EN SYSTEM
--------------------------------------------------------
--TABLE SPACE DATA 
CREATE TABLESPACE LAB1_DATA DATAFILE 'C:\Oracle18c\oradata\XE\LAB1_DATA.dbf' SIZE 52M   ONLINE;
-- TABLE SPACE TEMP
CREATE TEMPORARY TABLESPACE LAB1_TEMP TEMPFILE 'C:\Oracle18c\oradata\XE\LAB1_TEMP.dbf' SIZE 18M  AUTOEXTEND ON ;
--CREAR UN SCHEMA NUEVO 
  
alter session set "_ORACLE_SCRIPT" =true;   
CREATE USER LAB IDENTIFIED BY root
       DEFAULT TABLESPACE LAB1_DATA
       TEMPORARY TABLESPACE  LAB1_TEMP;
       
       
GRANT RESOURCE TO LAB; 
GRANT CONNECT TO LAB;
GRANT ALL PRIVILEGES TO LAB; 

--------------------------------------------------------
--  EJECUTAR EN EL NUEVO ESQUEMA
--------------------------------------------------------

CREATE TABLE CARRERAS(
    CODIGO NUMBER(10) NOT NULL,
    NOMBRE VARCHAR2(100),
    TITULO VARCHAR2(100),
    CONSTRAINT PK_COD_CARRERAS PRIMARY KEY (CODIGO)    
);


CREATE TABLE CURSOS(
    CODIGO NUMBER(10) NOT NULL,
    NOMBRE VARCHAR2(100),
    CREDITOS NUMBER(10),
    HORAS NUMBER(10),
    CONSTRAINT PK_COD_CURSOS PRIMARY KEY(CODIGO)
);


CREATE TABLE CARRERACURSOS(
    CODCARRERA NUMBER(10) NOT NULL,
    CODCURSOS NUMBER(10) NOT NULL,
    ANO VARCHAR2(100),
    CICLO VARCHAR2(100)
);
ALTER TABLE CARRERACURSOS ADD CONSTRAINT FK_COD_CARRERAS FOREIGN  KEY(CODCARRERA) REFERENCES CARRERAS(CODIGO);
ALTER TABLE CARRERACURSOS ADD CONSTRAINT FK_COD_CURSOS FOREIGN  KEY(CODCURSOS) REFERENCES CURSOS(CODIGO);


  CREATE OR REPLACE FORCE NONEDITIONABLE VIEW "LAB"."V_CARRERAS_CURSOS" ("CODCARRERA", "CODCURSOS", "ANO", "CICLO", "CARRERACODIGO", "CARRERANOMBRE", "TITULO", "CURSOCODIGO", "CURSONOMBRE", "CREDITOS", "HORAS") AS 
  SELECT
        cc.codcarrera   codcarrera,
        cc.codcursos    codcursos,
        cc.ano          ano,
        cc.ciclo        ciclo,
        ca.codigo       carreracodigo,
        ca.nombre       carreranombre,
        ca.titulo       titulo,
        cu.codigo       cursocodigo,
        cu.nombre       cursonombre,
        cu.creditos     creditos,
        cu.horas        horas
    FROM
        carreracursos   cc
        FULL OUTER JOIN carreras        ca ON ca.codigo = cc.codcarrera
        FULL OUTER JOIN cursos          cu ON cu.codigo = cc.codcursos;

--------------------------------------------------------
--  DDL for Procedure SP_INSERTACARRERA
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_INSERTACARRERA" (
    p_codigo   IN   NUMBER,
    p_nombre   IN   VARCHAR2,
    p_titulo   IN   VARCHAR2
) IS
BEGIN
    INSERT INTO carreras (
        codigo,
        nombre,
        titulo
    ) VALUES (
        p_codigo,
        p_nombre,
        p_titulo
    );

END sp_insertacarrera;
/
--------------------------------------------------------
--  DDL for Procedure SP_INSERTACURSOS
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_INSERTACURSOS" (
    p_codigo     IN   NUMBER,
    p_nombre     IN   VARCHAR2,
    p_creditos   IN   NUMBER,
    p_horas      IN   NUMBER
) IS
BEGIN
    INSERT INTO cursos (
        codigo,
        nombre,
        creditos,
        horas
    ) VALUES (
        p_codigo,
        p_nombre,
        p_creditos,
        p_horas
    );

END sp_insertacursos;
/
--------------------------------------------------------
--  DDL for Procedure SP_INSERTACARRERACURSOS
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_INSERTACARRERACURSOS" (
    p_codcarrera   IN   NUMBER,
    p_codcursos    IN   NUMBER,
    p_ano          IN   VARCHAR2,
    p_ciclo        IN   VARCHAR2
) IS
BEGIN
    INSERT INTO carreracursos (
        codcarrera,
        codcursos,
        ano,
        ciclo
    ) VALUES (
        p_codcarrera,
        p_codcursos,
        p_ano,
        p_ciclo
    );

END sp_insertacarreracursos;
/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATECARRERA
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_UPDATECARRERA" (
    p_codigo   IN   carreras.codigo%TYPE,
    p_nombre   IN   carreras.nombre%TYPE,
    p_titulo   IN   carreras.titulo%TYPE
) IS
BEGIN
    UPDATE carreras
    SET
        nombre = p_nombre,
        titulo = p_titulo
    WHERE
        codigo = p_codigo;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_updatecarrera;
/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATECURSOS
--------------------------------------------------------
CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_UPDATECURSOS" (
    p_codigo     IN   cursos.codigo%TYPE,
    p_nombre     IN   cursos.nombre%TYPE,
    p_creditos   IN   cursos.creditos%TYPE,
    p_horas      IN   cursos.horas%TYPE
) IS
BEGIN
    UPDATE cursos
    SET
        nombre = p_nombre,
        creditos = p_creditos,
        horas = p_horas
    WHERE
        codigo = p_codigo;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_updatecursos;
/
--------------------------------------------------------
--  DDL for Procedure SP_UPDATECARRERACURSOS
--------------------------------------------------------
CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_UPDATECARRERACURSOS" (
    p_codcarrera   IN   carreracursos.codcarrera%TYPE,
    p_codcursos    IN   carreracursos.codcursos%TYPE,
    p_ano          IN   carreracursos.ano%TYPE,
    p_ciclo        IN   carreracursos.ciclo%TYPE
) IS
BEGIN
    UPDATE carreracursos
    SET
        ano = p_ano,
        ciclo = p_ciclo
    WHERE
        codcarrera = p_codcarrera
        AND codcursos = p_codcursos;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_updatecarreracursos;
/
--------------------------------------------------------
--  DDL for Procedure SP_DELETECARRERAS
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_DELETECARRERAS" (
    p_codigo IN carreras.codigo%TYPE
) IS
BEGIN
    DELETE FROM carreras
    WHERE
        codigo = p_codigo;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_deletecarreras;
/
--------------------------------------------------------
--  DDL for Procedure SP_DELETECURSOS
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_DELETECURSOS" (
    p_codigo IN cursos.codigo%TYPE
) IS
BEGIN
    DELETE FROM CURSOS
    WHERE
        codigo = p_codigo;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_deletecursos;
/
--------------------------------------------------------
--  DDL for Procedure SP_DELETECARRERACURSOS
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "SP_DELETECARRERACURSOS" (
    p_codcarrera   IN   carreracursos.codcarrera%TYPE,
    p_codcursos    IN   carreracursos.codcursos%TYPE
) IS
BEGIN
    DELETE FROM carreracursos
    WHERE
        codcarrera = p_codcarrera
        AND codcursos = p_codcursos;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
END sp_deletecarreracursos;

/

--CREAR CURSOR
CREATE OR REPLACE PACKAGE TYPES

AS

TYPE REF_CURSOR IS REF CURSOR;

END;

/
----PROCESOS ALMACENADOS DE SELECT 

CREATE OR REPLACE NONEDITIONABLE FUNCTION listar_cursos RETURN types.ref_cursor AS
    cursor_cursos types.ref_cursor;
BEGIN
    OPEN cursor_cursos FOR SELECT
                               vcc.cursocodigo   codigo,
                               vcc.creditos      creditos,
                               vcc.horas         horas,
                               vcc.cursonombre   nombre,
                               vcc.ano           anho,
                               vcc.ciclo         ciclo
                           FROM
                               v_carreras_cursos vcc
                           WHERE
                               vcc.cursocodigo <> 0;

    RETURN cursor_cursos;
END;
/
-----------------------------------------
CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_curso (
    p_codigo IN v_carreras_cursos.cursocodigo%TYPE
) RETURN types.ref_cursor AS
    cursor_curso types.ref_cursor;
BEGIN
    OPEN cursor_curso FOR SELECT
                              vcc.cursocodigo   codigo,
                              vcc.creditos      creditos,
                              vcc.horas         horas,
                              vcc.cursonombre   nombre,
                              vcc.ano           anho,
                              vcc.ciclo         ciclo
                          FROM
                              v_carreras_cursos vcc
                          WHERE
                              vcc.cursocodigo = p_codigo;

    RETURN cursor_curso;
END;
/
-----------------------------------------
CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_curso_nombre (
    p_nombre IN v_carreras_cursos.cursonombre%TYPE
) RETURN types.ref_cursor AS
    cursor_curso types.ref_cursor;
BEGIN
    OPEN cursor_curso FOR SELECT
                              vcc.cursocodigo   codigo,
                              vcc.creditos      creditos,
                              vcc.horas         horas,
                              vcc.cursonombre   nombre,
                              vcc.ano           anho,
                              vcc.ciclo         ciclo
                          FROM
                              v_carreras_cursos vcc
                          WHERE
                              vcc.cursonombre = p_nombre;

    RETURN cursor_curso;
END;
/
---------------------------------------
CREATE OR REPLACE NONEDITIONABLE FUNCTION listar_carreras RETURN types.ref_cursor AS
    cursor_carreras types.ref_cursor;
BEGIN
    OPEN cursor_carreras FOR SELECT
                                 vcc.carreracodigo   codigo,
                                 vcc.carreranombre   nombre,
                                 vcc.titulo          titulo
                             FROM
                                 v_carreras_cursos vcc
                             WHERE
                                 vcc.carreracodigo <> 0;

    RETURN cursor_carreras;
END;
/
-----------------------------------------
CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_carrera (
    p_codigo IN carreras.codigo%TYPE
) RETURN types.ref_cursor AS
    cursor_carrera types.ref_cursor;
BEGIN
    OPEN cursor_carrera FOR SELECT
                                *
                            FROM
                                carreras
                            WHERE
                                codigo = p_codigo;

    RETURN cursor_carrera;
END;
/
---------------------------------------

CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_carrera_nombre (
    p_nombre IN carreras.nombre%TYPE
) RETURN types.ref_cursor AS
    cursor_carrera_nombre types.ref_cursor;
BEGIN
    OPEN cursor_carrera_nombre FOR SELECT
                                       codigo,
                                       nombre,
                                       titulo
                                   FROM
                                       carreras
                                   WHERE
                                       nombre = p_nombre;

    RETURN cursor_carrera_nombre;
END;
/

CREATE OR REPLACE NONEDITIONABLE FUNCTION buscar_carrera_cursos (
    p_codigo IN v_carreras_cursos.carreracodigo%TYPE
) RETURN types.ref_cursor AS
    cursor_carrera_cursos types.ref_cursor;
BEGIN
    OPEN cursor_carrera_cursos FOR SELECT
                                       vcc.cursocodigo   codigo,
                                       vcc.creditos      creditos,
                                       vcc.horas         horas,
                                       vcc.cursonombre   nombre,
                                       vcc.ano           anho,
                                       vcc.ciclo         ciclo
                                   FROM
                                       v_carreras_cursos vcc
                                   WHERE
                                       vcc.carreracodigo = p_codigo;

    RETURN cursor_carrera_cursos;
END;
/
BEGIN
   sp_insertacursos (01,'MOVILES',4,4);
   sp_insertacursos (02,'INTELIGENCIA',4,3);
   sp_insertacursos (03,'CONTA',3,4);
   sp_insertacursos (04,'RH',3,3);
   COMMIT;
END;

BEGIN 
    sp_insertacarrera(001,'INGENIERIA','BACHI');
    sp_insertacarrera(002,'ADMINISTRACION','BACHI');
    COMMIT;
END; 
/

BEGIN
    sp_insertacarreracursos(001,01,'2020','Iciclo');
    sp_insertacarreracursos(001,02,'2020','Iciclo');
    sp_insertacarreracursos(002,03,'2019','Iciclo');
    sp_insertacarreracursos(002,04,'2019','Iciclo');
    COMMIT;
END;