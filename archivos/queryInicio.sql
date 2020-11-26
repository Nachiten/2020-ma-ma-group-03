-- PASO 1: BORRADO DE LA BASE DE DATOS (ES LO MISMO QUE SCHEMA) SI SE LO REQUIERE
DROP database persistenciatp;
-- PASO 2: CREACION DE LA BASE DE DATOS (ES LO MISMO QUE SCHEMA) SI SE APLICÓ EL PASO 1
create database persistenciatp;
-- PASO 3: ACCEDEMOS A LA BASE DE DATOS CUANDO INICIAMOS
use persistenciatp; 
-- paso 4: REALIZAR UN REFRESH ALL EN SOLAPA NAVIGATOR
-- PASO 5: ACCEDEMOS MEDIANTE LAS SIGUIENTES CONSULTAS A CADA TABLA DE LA BASE DE DATOS
-- POR LAS DUDAS REALIZAR EL PASO 4 LAS VECES QUE SEA NECESARIA
SELECT * FROM usuario;
SELECT * FROM mensaje;
SELECT * FROM contraanterior;
SELECT * FROM operaciondeegreso;
SELECT * FROM operaciondeingreso;
SELECT * FROM proveedor;
SELECT * FROM mediodepago;
SELECT * FROM tipomediodepago;
SELECT * FROM presupuesto;
SELECT * FROM documentocomercial;
SELECT * FROM tipodocumentocomercial;
SELECT * FROM item;
SELECT * FROM presupuesto_item;
SELECT * FROM direccionpostal;
SELECT * FROM entidadjuridica;
SELECT * FROM tipoentidadjuridicaempresa;
SELECT * FROM sector;
SELECT * FROM categoriaEmpresa;
SELECT * FROM criterio;

SELECT * FROM paises;
SELECT * FROM provincias;
SELECT * FROM ciudades;
SELECT * FROM monedas;