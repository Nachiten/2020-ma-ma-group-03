-- MySQL dump 10.13  Distrib 5.7.31, for Win64 (x86_64)
--
-- Host: localhost    Database: persistenciatp
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoriacriterio`
--

DROP TABLE IF EXISTS `categoriacriterio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriacriterio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombreCategoria` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriacriterio`
--

LOCK TABLES `categoriacriterio` WRITE;
/*!40000 ALTER TABLE `categoriacriterio` DISABLE KEYS */;
INSERT INTO `categoriacriterio` VALUES (1,'Articulos de oficina','Oficina'),(2,'Articulos de limpieza','Limpieza'),(3,'Articulos de seguridad','Seguridad'),(4,'Cosas edilicias','Edilicio'),(5,'Reparacion','Tecnico'),(6,'Computadoras','Software'),(7,'Dentro del pais','Nacional'),(8,'Fuera del pais','Internacional'),(9,'Los dos anteriores','Ambos'),(10,'Mayor a 1M','Caro'),(11,'Entre 100k y 1M','Intermedio'),(12,'Menor a 100k','Barato'),(13,'Dentro de la empresa','Interno'),(14,'Fuera de la empresa','Externo');
/*!40000 ALTER TABLE `categoriacriterio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoriaempresa`
--

DROP TABLE IF EXISTS `categoriaempresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoriaempresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidadPersonalMaximo` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `ventasAnualesMaximas` bigint(20) DEFAULT NULL,
  `sectorAsociado_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_67tyxhkjf5sswji4e7e2at8m` (`sectorAsociado_id`),
  CONSTRAINT `FK_67tyxhkjf5sswji4e7e2at8m` FOREIGN KEY (`sectorAsociado_id`) REFERENCES `sector` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoriaempresa`
--

LOCK TABLES `categoriaempresa` WRITE;
/*!40000 ALTER TABLE `categoriaempresa` DISABLE KEYS */;
INSERT INTO `categoriaempresa` VALUES (1,12,'Micro',19450000,1),(2,45,'Pequeña',115370000,1),(3,200,'MedianaTramo1',643710000,1),(4,590,'MedianaTramo2',965460000,1),(5,7,'Micro',9900000,2),(6,30,'Pequeña',59710000,2),(7,165,'MedianaTramo1',494200000,2),(8,535,'MedianaTramo2',705790000,2),(9,7,'Micro',36320000,3),(10,35,'Pequeña',247200000,3),(11,125,'MedianaTramo1',1821760000,3),(12,345,'MedianaTramo2',2602540000,3),(13,15,'Micro',33920000,4),(14,60,'Pequeña',243290000,4),(15,235,'MedianaTramo1',1651750000,4),(16,655,'MedianaTramo2',2540380000,4),(17,5,'Micro',17260000,5),(18,10,'Pequeña',71960000,5),(19,50,'MedianaTramo1',426720000,5),(20,215,'MedianaTramo2',676810000,5);
/*!40000 ALTER TABLE `categoriaempresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudades` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provinciaAsociada_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hcyx09ed7osvudqstqmrsyh4s` (`provinciaAsociada_id`),
  CONSTRAINT `FK_hcyx09ed7osvudqstqmrsyh4s` FOREIGN KEY (`provinciaAsociada_id`) REFERENCES `provincias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contraanterior`
--

DROP TABLE IF EXISTS `contraanterior`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contraanterior` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contraAnterior` varchar(255) DEFAULT NULL,
  `nombreUsuarioAsociado` varchar(255) DEFAULT NULL,
  `tiempoContrasenia` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contraanterior`
--

LOCK TABLES `contraanterior` WRITE;
/*!40000 ALTER TABLE `contraanterior` DISABLE KEYS */;
INSERT INTO `contraanterior` VALUES (1,'jorge123','Nachiten','2015-07-29'),(2,'fernando456','Nachiten','2017-02-05');
/*!40000 ALTER TABLE `contraanterior` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criterio`
--

DROP TABLE IF EXISTS `criterio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criterio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `criterioPadre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a3im7g0t6an46lycapbnqqpac` (`criterioPadre_id`),
  CONSTRAINT `FK_a3im7g0t6an46lycapbnqqpac` FOREIGN KEY (`criterioPadre_id`) REFERENCES `criterio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criterio`
--

LOCK TABLES `criterio` WRITE;
/*!40000 ALTER TABLE `criterio` DISABLE KEYS */;
INSERT INTO `criterio` VALUES (1,'Insumos',NULL),(2,'Mantenimiento',NULL),(3,'Alcance',NULL),(4,'Valor',NULL),(5,'Cliente',NULL);
/*!40000 ALTER TABLE `criterio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criterio_categoriacriterio`
--

DROP TABLE IF EXISTS `criterio_categoriacriterio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criterio_categoriacriterio` (
  `criterio_id` int(11) NOT NULL,
  `listaCategoriaCriterio_id` int(11) NOT NULL,
  UNIQUE KEY `UK_16ystg4nbfv5p2ni0r1el23qk` (`listaCategoriaCriterio_id`),
  KEY `FK_id141vpkakj4qon0npq5oqp2v` (`criterio_id`),
  CONSTRAINT `FK_16ystg4nbfv5p2ni0r1el23qk` FOREIGN KEY (`listaCategoriaCriterio_id`) REFERENCES `categoriacriterio` (`id`),
  CONSTRAINT `FK_id141vpkakj4qon0npq5oqp2v` FOREIGN KEY (`criterio_id`) REFERENCES `criterio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criterio_categoriacriterio`
--

LOCK TABLES `criterio_categoriacriterio` WRITE;
/*!40000 ALTER TABLE `criterio_categoriacriterio` DISABLE KEYS */;
INSERT INTO `criterio_categoriacriterio` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6),(3,7),(3,8),(3,9),(4,10),(4,11),(4,12),(5,13),(5,14);
/*!40000 ALTER TABLE `criterio_categoriacriterio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(255) DEFAULT NULL,
  `dpto` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `piso` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccionpostal`
--

DROP TABLE IF EXISTS `direccionpostal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccionpostal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `direccion_id` int(11) DEFAULT NULL,
  `pais_id` varchar(255) DEFAULT NULL,
  `provincia_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h6myskeuii0vfn7wuhtqwytgs` (`direccion_id`),
  KEY `FK_i4kb8etk84rtaqeqrmqm0v955` (`pais_id`),
  KEY `FK_8ouibehpcuew6m2i5rtm714g` (`provincia_id`),
  CONSTRAINT `FK_8ouibehpcuew6m2i5rtm714g` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`id`),
  CONSTRAINT `FK_h6myskeuii0vfn7wuhtqwytgs` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`),
  CONSTRAINT `FK_i4kb8etk84rtaqeqrmqm0v955` FOREIGN KEY (`pais_id`) REFERENCES `paises` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccionpostal`
--

LOCK TABLES `direccionpostal` WRITE;
/*!40000 ALTER TABLE `direccionpostal` DISABLE KEYS */;
/*!40000 ALTER TABLE `direccionpostal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentocomercial`
--

DROP TABLE IF EXISTS `documentocomercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentocomercial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` int(11) DEFAULT NULL,
  `tipoDocumentoComercial_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_28fhd6ivbj2x5pwepcoje2aiy` (`tipoDocumentoComercial_id`),
  CONSTRAINT `FK_28fhd6ivbj2x5pwepcoje2aiy` FOREIGN KEY (`tipoDocumentoComercial_id`) REFERENCES `tipodocumentocomercial` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentocomercial`
--

LOCK TABLES `documentocomercial` WRITE;
/*!40000 ALTER TABLE `documentocomercial` DISABLE KEYS */;
INSERT INTO `documentocomercial` VALUES (1,43,4);
/*!40000 ALTER TABLE `documentocomercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadbase`
--

DROP TABLE IF EXISTS `entidadbase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidadbase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombreFicticio` varchar(255) DEFAULT NULL,
  `razonSocial` varchar(255) DEFAULT NULL,
  `entidadJuridicaAsociada_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2j0lqc683wt6busqtxauqjrvv` (`entidadJuridicaAsociada_id`),
  CONSTRAINT `FK_2j0lqc683wt6busqtxauqjrvv` FOREIGN KEY (`entidadJuridicaAsociada_id`) REFERENCES `entidadjuridica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadbase`
--

LOCK TABLES `entidadbase` WRITE;
/*!40000 ALTER TABLE `entidadbase` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidadbase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadjuridica`
--

DROP TABLE IF EXISTS `entidadjuridica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidadjuridica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigoInscripcionDefinitiva` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombreFicticio` varchar(255) DEFAULT NULL,
  `razonSocial` varchar(255) DEFAULT NULL,
  `direccionPostal_id` int(11) DEFAULT NULL,
  `tipoEntidadJuridica_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1j65j5l9m0blfd6tfem8kjf5` (`direccionPostal_id`),
  CONSTRAINT `FK_b1j65j5l9m0blfd6tfem8kjf5` FOREIGN KEY (`direccionPostal_id`) REFERENCES `direccionpostal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadjuridica`
--

LOCK TABLES `entidadjuridica` WRITE;
/*!40000 ALTER TABLE `entidadjuridica` DISABLE KEYS */;
INSERT INTO `entidadjuridica` VALUES (1,'ABC-JFK','20345678','ConstructoraBA','ConstructuraBA','Constructora Bs.As S.A.',NULL,1);
/*!40000 ALTER TABLE `entidadjuridica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadjuridica_operaciondeegreso`
--

DROP TABLE IF EXISTS `entidadjuridica_operaciondeegreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidadjuridica_operaciondeegreso` (
  `entidadJuridica_id` int(11) NOT NULL,
  `operacionesDeEgreso_idOperacion` int(11) NOT NULL,
  UNIQUE KEY `UK_79fqyob0cg1o575nc6pdxbbm0` (`operacionesDeEgreso_idOperacion`),
  KEY `FK_fnoa9e6wuh4u9y2a6umu3aprj` (`entidadJuridica_id`),
  CONSTRAINT `FK_79fqyob0cg1o575nc6pdxbbm0` FOREIGN KEY (`operacionesDeEgreso_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`),
  CONSTRAINT `FK_fnoa9e6wuh4u9y2a6umu3aprj` FOREIGN KEY (`entidadJuridica_id`) REFERENCES `entidadjuridica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadjuridica_operaciondeegreso`
--

LOCK TABLES `entidadjuridica_operaciondeegreso` WRITE;
/*!40000 ALTER TABLE `entidadjuridica_operaciondeegreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidadjuridica_operaciondeegreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadjuridica_operaciondeingreso`
--

DROP TABLE IF EXISTS `entidadjuridica_operaciondeingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidadjuridica_operaciondeingreso` (
  `entidadJuridica_id` int(11) NOT NULL,
  `operacionesDeIngreso_id` int(11) NOT NULL,
  UNIQUE KEY `UK_k0wahswwunbajlrbnchtxpnwq` (`operacionesDeIngreso_id`),
  KEY `FK_5vp0de1vl43alta9dqh3wnb2t` (`entidadJuridica_id`),
  CONSTRAINT `FK_5vp0de1vl43alta9dqh3wnb2t` FOREIGN KEY (`entidadJuridica_id`) REFERENCES `entidadjuridica` (`id`),
  CONSTRAINT `FK_k0wahswwunbajlrbnchtxpnwq` FOREIGN KEY (`operacionesDeIngreso_id`) REFERENCES `operaciondeingreso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadjuridica_operaciondeingreso`
--

LOCK TABLES `entidadjuridica_operaciondeingreso` WRITE;
/*!40000 ALTER TABLE `entidadjuridica_operaciondeingreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidadjuridica_operaciondeingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('TipoEntidadJuridica',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` float DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipoItem` varchar(255) DEFAULT NULL,
  `valor` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,0,'Remera Talle L',NULL,1000),(2,0,'Pantalon Talle 42',NULL,1550),(3,0,'Remera Talle S',NULL,600),(4,0,'Remera Talle M',NULL,800),(5,0,'Pantalon Talle 44',NULL,1650);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mediodepago`
--

DROP TABLE IF EXISTS `mediodepago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mediodepago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(255) DEFAULT NULL,
  `tipoMedioPago_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_s3nc79spjs5a4lw8vohhgcjmy` (`tipoMedioPago_id`),
  CONSTRAINT `FK_s3nc79spjs5a4lw8vohhgcjmy` FOREIGN KEY (`tipoMedioPago_id`) REFERENCES `tipomediodepago` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mediodepago`
--

LOCK TABLES `mediodepago` WRITE;
/*!40000 ALTER TABLE `mediodepago` DISABLE KEYS */;
INSERT INTO `mediodepago` VALUES (1,'9468753',1);
/*!40000 ALTER TABLE `mediodepago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensaje`
--

DROP TABLE IF EXISTS `mensaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contenido` varchar(255) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `fechaLectura` datetime DEFAULT NULL,
  `leido` bit(1) DEFAULT NULL,
  `nombreUsuarioAsociado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensaje`
--

LOCK TABLES `mensaje` WRITE;
/*!40000 ALTER TABLE `mensaje` DISABLE KEYS */;
INSERT INTO `mensaje` VALUES (1,'La operacion de egreso: Un resultado 1 tiene resultado true','2020-11-16 23:48:45',NULL,_binary '\0','Carlos'),(2,'La operacion de egreso: Un resultado 2 tiene resultado false','2020-11-16 23:48:45',NULL,_binary '\0','Carlos'),(3,'La operacion de egreso: Un resultado 3 tiene resultado true','2020-11-16 23:48:45',NULL,_binary '\0','Carlos');
/*!40000 ALTER TABLE `mensaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monedas`
--

DROP TABLE IF EXISTS `monedas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monedas` (
  `id` varchar(255) NOT NULL,
  `decimales` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `simbolo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monedas`
--

LOCK TABLES `monedas` WRITE;
/*!40000 ALTER TABLE `monedas` DISABLE KEYS */;
/*!40000 ALTER TABLE `monedas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeegreso`
--

DROP TABLE IF EXISTS `operaciondeegreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeegreso` (
  `idOperacion` int(11) NOT NULL AUTO_INCREMENT,
  `cantidadPresupuestosRequerida` int(11) DEFAULT NULL,
  `entidadJuridica_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `montoTotal` float DEFAULT NULL,
  `operacionDeIngreso` int(11) DEFAULT NULL,
  `documentoComercial_id` int(11) DEFAULT NULL,
  `medioDePago_id` int(11) DEFAULT NULL,
  `proveedorAsociado_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) NOT NULL,
  PRIMARY KEY (`idOperacion`),
  KEY `FK_bscxh0d93nc9mm8e4355pysdy` (`documentoComercial_id`),
  KEY `FK_9f9wr4v0gowu67u80jnfa0jep` (`medioDePago_id`),
  KEY `FK_37x9qippocytr3gx2lre2f5yg` (`proveedorAsociado_id`),
  KEY `FK_lo0kf7viuycogcbwad8qlayse` (`usuario_id`),
  CONSTRAINT `FK_37x9qippocytr3gx2lre2f5yg` FOREIGN KEY (`proveedorAsociado_id`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `FK_9f9wr4v0gowu67u80jnfa0jep` FOREIGN KEY (`medioDePago_id`) REFERENCES `mediodepago` (`id`),
  CONSTRAINT `FK_bscxh0d93nc9mm8e4355pysdy` FOREIGN KEY (`documentoComercial_id`) REFERENCES `documentocomercial` (`id`),
  CONSTRAINT `FK_lo0kf7viuycogcbwad8qlayse` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeegreso`
--

LOCK TABLES `operaciondeegreso` WRITE;
/*!40000 ALTER TABLE `operaciondeegreso` DISABLE KEYS */;
INSERT INTO `operaciondeegreso` VALUES (1,1,1,'2020-11-16',5600,0,1,1,1,2);
/*!40000 ALTER TABLE `operaciondeegreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeegreso_categoriacriterio`
--

DROP TABLE IF EXISTS `operaciondeegreso_categoriacriterio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeegreso_categoriacriterio` (
  `operacionDeEgreso_idOperacion` int(11) NOT NULL,
  `listaCategoriaCriterio_id` int(11) NOT NULL,
  KEY `FK_ibhcka042yr805puv35aclw5e` (`listaCategoriaCriterio_id`),
  KEY `FK_6y56thnlhrcqst39c968revi9` (`operacionDeEgreso_idOperacion`),
  CONSTRAINT `FK_6y56thnlhrcqst39c968revi9` FOREIGN KEY (`operacionDeEgreso_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`),
  CONSTRAINT `FK_ibhcka042yr805puv35aclw5e` FOREIGN KEY (`listaCategoriaCriterio_id`) REFERENCES `categoriacriterio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeegreso_categoriacriterio`
--

LOCK TABLES `operaciondeegreso_categoriacriterio` WRITE;
/*!40000 ALTER TABLE `operaciondeegreso_categoriacriterio` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciondeegreso_categoriacriterio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeegreso_item`
--

DROP TABLE IF EXISTS `operaciondeegreso_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeegreso_item` (
  `operacionDeEgreso_idOperacion` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  KEY `FK_wlxtfxew42n5t32aaf8efphu` (`items_id`),
  KEY `FK_r5uj9tb3nij3nu7u03jfd1ges` (`operacionDeEgreso_idOperacion`),
  CONSTRAINT `FK_r5uj9tb3nij3nu7u03jfd1ges` FOREIGN KEY (`operacionDeEgreso_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`),
  CONSTRAINT `FK_wlxtfxew42n5t32aaf8efphu` FOREIGN KEY (`items_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeegreso_item`
--

LOCK TABLES `operaciondeegreso_item` WRITE;
/*!40000 ALTER TABLE `operaciondeegreso_item` DISABLE KEYS */;
INSERT INTO `operaciondeegreso_item` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `operaciondeegreso_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeegreso_presupuesto`
--

DROP TABLE IF EXISTS `operaciondeegreso_presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeegreso_presupuesto` (
  `operacionDeEgreso_idOperacion` int(11) NOT NULL,
  `presupuestos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_eky2meu7ual473nn8183oa07o` (`presupuestos_id`),
  KEY `FK_7mxualfyv4yq3hn7gb6apmibl` (`operacionDeEgreso_idOperacion`),
  CONSTRAINT `FK_7mxualfyv4yq3hn7gb6apmibl` FOREIGN KEY (`operacionDeEgreso_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`),
  CONSTRAINT `FK_eky2meu7ual473nn8183oa07o` FOREIGN KEY (`presupuestos_id`) REFERENCES `presupuesto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeegreso_presupuesto`
--

LOCK TABLES `operaciondeegreso_presupuesto` WRITE;
/*!40000 ALTER TABLE `operaciondeegreso_presupuesto` DISABLE KEYS */;
INSERT INTO `operaciondeegreso_presupuesto` VALUES (1,1);
/*!40000 ALTER TABLE `operaciondeegreso_presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeegreso_usuario`
--

DROP TABLE IF EXISTS `operaciondeegreso_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeegreso_usuario` (
  `operacionDeEgreso_idOperacion` int(11) NOT NULL,
  `revisores_id` int(11) NOT NULL,
  KEY `FK_ht0bwpdg5u6yu41q7vu0povc` (`revisores_id`),
  KEY `FK_1450frvtkq6phstardf0klluh` (`operacionDeEgreso_idOperacion`),
  CONSTRAINT `FK_1450frvtkq6phstardf0klluh` FOREIGN KEY (`operacionDeEgreso_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`),
  CONSTRAINT `FK_ht0bwpdg5u6yu41q7vu0povc` FOREIGN KEY (`revisores_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeegreso_usuario`
--

LOCK TABLES `operaciondeegreso_usuario` WRITE;
/*!40000 ALTER TABLE `operaciondeegreso_usuario` DISABLE KEYS */;
INSERT INTO `operaciondeegreso_usuario` VALUES (1,2),(1,1);
/*!40000 ALTER TABLE `operaciondeegreso_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeingreso`
--

DROP TABLE IF EXISTS `operaciondeingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeingreso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `entidadJuridica_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `montoSinVincular` float DEFAULT NULL,
  `montoTotal` float DEFAULT NULL,
  `periodoAceptacion` date DEFAULT NULL,
  `moneda_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7v05jrcawvyfuvmkehhjbvk0i` (`moneda_id`),
  CONSTRAINT `FK_7v05jrcawvyfuvmkehhjbvk0i` FOREIGN KEY (`moneda_id`) REFERENCES `monedas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeingreso`
--

LOCK TABLES `operaciondeingreso` WRITE;
/*!40000 ALTER TABLE `operaciondeingreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciondeingreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciondeingreso_operaciondeegreso`
--

DROP TABLE IF EXISTS `operaciondeingreso_operaciondeegreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciondeingreso_operaciondeegreso` (
  `operacionDeIngreso_id` int(11) NOT NULL,
  `operacionesDeEgresoVinculadas_idOperacion` int(11) NOT NULL,
  UNIQUE KEY `UK_cpa29kf5pwculwbr96d2lngh4` (`operacionesDeEgresoVinculadas_idOperacion`),
  KEY `FK_bur9bhjs185cpgwgl3gv6lnjw` (`operacionDeIngreso_id`),
  CONSTRAINT `FK_bur9bhjs185cpgwgl3gv6lnjw` FOREIGN KEY (`operacionDeIngreso_id`) REFERENCES `operaciondeingreso` (`id`),
  CONSTRAINT `FK_cpa29kf5pwculwbr96d2lngh4` FOREIGN KEY (`operacionesDeEgresoVinculadas_idOperacion`) REFERENCES `operaciondeegreso` (`idOperacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciondeingreso_operaciondeegreso`
--

LOCK TABLES `operaciondeingreso_operaciondeegreso` WRITE;
/*!40000 ALTER TABLE `operaciondeingreso_operaciondeegreso` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciondeingreso_operaciondeegreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `id` varchar(255) NOT NULL,
  `currency_id` varchar(255) DEFAULT NULL,
  `locale` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `montoTotal` float DEFAULT NULL,
  `operacionDeEgreso_id` int(11) DEFAULT NULL,
  `documentoComercial_id` int(11) DEFAULT NULL,
  `proveedorAsociado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clyqbp9a6igsv7eu0ev5tjcqf` (`documentoComercial_id`),
  KEY `FK_3ycoqhgbhbq3ukt6hsktmhcxp` (`proveedorAsociado_id`),
  CONSTRAINT `FK_3ycoqhgbhbq3ukt6hsktmhcxp` FOREIGN KEY (`proveedorAsociado_id`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `FK_clyqbp9a6igsv7eu0ev5tjcqf` FOREIGN KEY (`documentoComercial_id`) REFERENCES `documentocomercial` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,5600,0,NULL,NULL);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_categoriacriterio`
--

DROP TABLE IF EXISTS `presupuesto_categoriacriterio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto_categoriacriterio` (
  `presupuesto_id` int(11) NOT NULL,
  `listaCategoriaCriterio_id` int(11) NOT NULL,
  KEY `FK_jvb75u6ibnuns8d816cg0uw62` (`listaCategoriaCriterio_id`),
  KEY `FK_r0xwqf6ojy2qvbftqudgixdf7` (`presupuesto_id`),
  CONSTRAINT `FK_jvb75u6ibnuns8d816cg0uw62` FOREIGN KEY (`listaCategoriaCriterio_id`) REFERENCES `categoriacriterio` (`id`),
  CONSTRAINT `FK_r0xwqf6ojy2qvbftqudgixdf7` FOREIGN KEY (`presupuesto_id`) REFERENCES `presupuesto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_categoriacriterio`
--

LOCK TABLES `presupuesto_categoriacriterio` WRITE;
/*!40000 ALTER TABLE `presupuesto_categoriacriterio` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuesto_categoriacriterio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_item`
--

DROP TABLE IF EXISTS `presupuesto_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presupuesto_item` (
  `presupuesto_id` int(11) NOT NULL,
  `items_id` int(11) NOT NULL,
  KEY `FK_7ci41ht037ijjkvvj07pbeaqr` (`items_id`),
  KEY `FK_5h7a6ctifato2sdqy8b097pub` (`presupuesto_id`),
  CONSTRAINT `FK_5h7a6ctifato2sdqy8b097pub` FOREIGN KEY (`presupuesto_id`) REFERENCES `presupuesto` (`id`),
  CONSTRAINT `FK_7ci41ht037ijjkvvj07pbeaqr` FOREIGN KEY (`items_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_item`
--

LOCK TABLES `presupuesto_item` WRITE;
/*!40000 ALTER TABLE `presupuesto_item` DISABLE KEYS */;
INSERT INTO `presupuesto_item` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `presupuesto_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `estoyHabilitado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `razonSocial` varchar(255) DEFAULT NULL,
  `direccionPostal_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_foys93lubgktkvw9c7kfeuy8l` (`direccionPostal_id`),
  CONSTRAINT `FK_foys93lubgktkvw9c7kfeuy8l` FOREIGN KEY (`direccionPostal_id`) REFERENCES `direccionpostal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'Fernandez',NULL,42374333,_binary '','Roberto','RopaLinda S.A.',NULL),(2,'Carla',NULL,41374383,_binary '','Ramirez','Contruccion SRL',NULL),(3,'Fernando',NULL,42954333,_binary '','Baptista','Cosas SA',NULL),(4,'Julian',NULL,36374333,_binary '','Martinez','Alimentos SA',NULL);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `paisAsociado_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_m97fitxp7pcq7bcurvfb9lypx` (`paisAsociado_id`),
  CONSTRAINT `FK_m97fitxp7pcq7bcurvfb9lypx` FOREIGN KEY (`paisAsociado_id`) REFERENCES `paises` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sector`
--

DROP TABLE IF EXISTS `sector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sector`
--

LOCK TABLES `sector` WRITE;
/*!40000 ALTER TABLE `sector` DISABLE KEYS */;
INSERT INTO `sector` VALUES (1,'Construccion'),(2,'Servicios'),(3,'Comercio'),(4,'Industria Y Mineria'),(5,'Agropecuario');
/*!40000 ALTER TABLE `sector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodocumentocomercial`
--

DROP TABLE IF EXISTS `tipodocumentocomercial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodocumentocomercial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodocumentocomercial`
--

LOCK TABLES `tipodocumentocomercial` WRITE;
/*!40000 ALTER TABLE `tipodocumentocomercial` DISABLE KEYS */;
INSERT INTO `tipodocumentocomercial` VALUES (1,'Factura'),(2,'Nota de credito'),(3,'Nota de debito'),(4,'Recibo'),(5,'Cheque'),(6,'Pagare'),(7,'Orden de compra');
/*!40000 ALTER TABLE `tipodocumentocomercial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoentidadjuridicaempresa`
--

DROP TABLE IF EXISTS `tipoentidadjuridicaempresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoentidadjuridicaempresa` (
  `id` int(11) NOT NULL,
  `nombreFicticio` varchar(255) DEFAULT NULL,
  `cantidadPersonal` int(11) DEFAULT NULL,
  `promedioVentasAnuales` int(11) DEFAULT NULL,
  `categoria_id` int(11) DEFAULT NULL,
  `sector_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cd7c2bn978yljuem80cuxrwkx` (`categoria_id`),
  KEY `FK_iqhdnlfnus4vqm2h9gh6qp1ip` (`sector_id`),
  CONSTRAINT `FK_cd7c2bn978yljuem80cuxrwkx` FOREIGN KEY (`categoria_id`) REFERENCES `categoriaempresa` (`id`),
  CONSTRAINT `FK_iqhdnlfnus4vqm2h9gh6qp1ip` FOREIGN KEY (`sector_id`) REFERENCES `sector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoentidadjuridicaempresa`
--

LOCK TABLES `tipoentidadjuridicaempresa` WRITE;
/*!40000 ALTER TABLE `tipoentidadjuridicaempresa` DISABLE KEYS */;
INSERT INTO `tipoentidadjuridicaempresa` VALUES (1,'Corralon',44,115360000,2,1);
/*!40000 ALTER TABLE `tipoentidadjuridicaempresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoentidadjuridicaorganizacionsectorsocial`
--

DROP TABLE IF EXISTS `tipoentidadjuridicaorganizacionsectorsocial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoentidadjuridicaorganizacionsectorsocial` (
  `id` int(11) NOT NULL,
  `nombreFicticio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoentidadjuridicaorganizacionsectorsocial`
--

LOCK TABLES `tipoentidadjuridicaorganizacionsectorsocial` WRITE;
/*!40000 ALTER TABLE `tipoentidadjuridicaorganizacionsectorsocial` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoentidadjuridicaorganizacionsectorsocial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipomediodepago`
--

DROP TABLE IF EXISTS `tipomediodepago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipomediodepago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoPago` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipomediodepago`
--

LOCK TABLES `tipomediodepago` WRITE;
/*!40000 ALTER TABLE `tipomediodepago` DISABLE KEYS */;
INSERT INTO `tipomediodepago` VALUES (1,'Tarjeta de credito'),(2,'Tarjeta de debito'),(3,'Ticket'),(4,'ATM'),(5,'Efectivo');
/*!40000 ALTER TABLE `tipomediodepago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `contraseniaEncriptada` varchar(255) DEFAULT NULL,
  `entidadJuridica_id` int(11) DEFAULT NULL,
  `estoyHabilitado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `nombreUsuario` varchar(255) DEFAULT NULL,
  `idOperacion` int(11) DEFAULT NULL,
  `soyRevisor` bit(1) DEFAULT NULL,
  `tiempoUltimaContrasenia` date DEFAULT NULL,
  `tipoUsuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Villegas','040b7cf4a55014e185813e0644502ea9',1,_binary '','Carlos','Carlos',0,_binary '\0',NULL,'ESTANDAR'),(2,'Baptista','b032028a0b5e7f409d166745374108ec',1,_binary '','Ignacio','Nachiten',0,_binary '\0','2015-07-29','ESTANDAR'),(3,'Root','21232f297a57a5a743894a0e4a801fc3',0,_binary '','Admin','Admin',NULL,_binary '\0',NULL,'ADMIN'),(4,'Lopez','39ce7d6456fcd534f43e56402795a904',1,_binary '\0','Eduardo','Edu',NULL,_binary '\0',NULL,'ESTANDAR');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-16 23:48:59
