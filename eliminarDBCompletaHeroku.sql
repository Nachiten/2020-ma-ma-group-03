-- Tablas Intermedias
DROP TABLE criterio_categoriacriterio;
DROP TABLE entidades_operaciondeegreso;
DROP TABLE entidades_operaciondeingreso;
DROP TABLE operaciondeegreso_categoriacriterio;
DROP TABLE operaciondeegreso_item;
DROP TABLE operaciondeegreso_presupuesto;
DROP TABLE operaciondeegreso_usuario;
DROP TABLE operaciondeingreso_operaciondeegreso;
DROP TABLE presupuesto_categoriacriterio;
DROP TABLE presupuesto_item;
DROP TABLE usuario_operaciondeegreso;

-- Criterios
DROP TABLE categoriacriterio;
DROP TABLE criterio;

-- Otros
DROP TABLE entidadbase;
DROP TABLE item;
DROP TABLE mensaje;
DROP TABLE contraanterior;
DROP TABLE presupuesto;

-- Operaciones
DROP TABLE operaciondeegreso;
DROP TABLE operaciondeingreso;
DROP TABLE criterioproveedormenorvalor;
DROP TABLE proveedor;

-- Documento comercial
DROP TABLE documentocomercial;
DROP TABLE tipodocumentocomercial;

-- Medio pago
DROP TABLE mediodepago;
DROP TABLE tipomediodepago;

-- Tipo entidad juridica
DROP TABLE usuario;
DROP TABLE entidades;
DROP TABLE tipoentidadjuridicaempresa;
DROP TABLE tipoentidadjuridicaorganizacionsectorsocial;
DROP TABLE categoriaempresa;
DROP TABLE sector;
DROP TABLE entidadjuridica;

-- Direccion postal
DROP TABLE direccionpostal;
DROP TABLE direccion;
DROP TABLE ciudades;
DROP TABLE provincias;
DROP TABLE paises;
DROP TABLE monedas;

-- Tabla de hibernate
DROP TABLE hibernate_sequences