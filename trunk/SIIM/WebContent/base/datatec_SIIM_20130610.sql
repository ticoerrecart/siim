CREATE DATABASE  IF NOT EXISTS `x071vm20_siim` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `x071vm20_siim`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: x071vm20_siim
-- ------------------------------------------------------
-- Server version	5.1.50-community

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
-- Table structure for table `entidad`
--

DROP TABLE IF EXISTS `entidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidad` (
  `tipoEntidad` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigoPostal` int(11) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `nroMatricula` bigint(20) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `localidad_fk` bigint(20) DEFAULT NULL,
  `cuil` varchar(255) DEFAULT NULL,
  `dni` int(11) DEFAULT NULL,
  `tipoDocumento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK456F1C9C8FC0F36` (`localidad_fk`),
  CONSTRAINT `FK456F1C9C8FC0F36` FOREIGN KEY (`localidad_fk`) REFERENCES `localidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES ('PRD',2,1951,'','Rivera 345','rgonzalez@gmail.com','Roberto Gonzalez',65465,'(0546)456-7899',3,'20111111155',0,'CUIL'),('RN',3,1900,NULL,'Gomez 987','rminerosrg@gmail.com','Recursos Mineros Rio Grande',654,'65454',1,'20111111155',0,'CUIL'),('RN',4,0,'20111111112','qweqw','rminerosu@gmail.com','Recursos Mineros Ushuaia',0,'09809',2,'',0,'CUIT'),('PRD',5,1987,NULL,'Ezpetela 900','miner@gmail.com','Miner S.A.',6578,'(0225)321-6547',3,'20111111112',0,'CUIL');
/*!40000 ALTER TABLE `entidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'SuperAdministrador'),(2,'Administrador'),(3,'Agente Tipo1');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_item`
--

DROP TABLE IF EXISTS `rol_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_item` (
  `rol_fk` bigint(20) NOT NULL,
  `item_fk` bigint(20) NOT NULL,
  KEY `FKEFD14383C90AF6` (`rol_fk`),
  KEY `FKEFD14383858F03D1` (`item_fk`),
  CONSTRAINT `FKEFD14383858F03D1` FOREIGN KEY (`item_fk`) REFERENCES `itemmenu` (`id`),
  CONSTRAINT `FKEFD14383C90AF6` FOREIGN KEY (`rol_fk`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_item`
--

LOCK TABLES `rol_item` WRITE;
/*!40000 ALTER TABLE `rol_item` DISABLE KEYS */;
INSERT INTO `rol_item` VALUES (3,2),(3,4),(3,11),(3,12),(3,18),(3,19),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23),(2,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30);
/*!40000 ALTER TABLE `rol_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `periodo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (1,'2011'),(2,'2012'),(3,'2013'),(4,'2014');
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localizacion`
--

DROP TABLE IF EXISTS `localizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localizacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entidad_fk` bigint(20) DEFAULT NULL,
  `expediente` varchar(255) DEFAULT NULL,
  `razonSocial` varchar(255) DEFAULT NULL,
  `resolucion` varchar(255) DEFAULT NULL,
  `superficie` double DEFAULT NULL,
  `domicilio` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD95D606AAE3B93B6` (`entidad_fk`),
  CONSTRAINT `FKD95D606AAE3B93B6` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localizacion`
--

LOCK TABLES `localizacion` WRITE;
/*!40000 ALTER TABLE `localizacion` DISABLE KEYS */;
INSERT INTO `localizacion` VALUES (1,2,'789-NSS','Yasimiento 11','4557',195,'ruta 3 km4567'),(3,2,'555-AQW','Yasimiento 2','12',120,'ruta 3 km4580'),(4,5,'741','YasMiner1','579',125,'ruta 3 km4600'),(6,5,'741/7','YasMiner2','1212',97,'ruta 3 km4205');
/*!40000 ALTER TABLE `localizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `habilitado` bit(1) NOT NULL,
  `nombreUsuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol_fk` bigint(20) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5B4D8B0EC90AF6` (`rol_fk`),
  KEY `FK5B4D8B0EAE3B93B6` (`entidad_fk`),
  CONSTRAINT `FK5B4D8B0EAE3B93B6` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK5B4D8B0EC90AF6` FOREIGN KEY (`rol_fk`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'','c','c',2,3),(2,'','a','a',3,4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemmenu`
--

DROP TABLE IF EXISTS `itemmenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemmenu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item` varchar(255) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `item_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4A06A732858F03D1` (`item_fk`),
  CONSTRAINT `FK4A06A732858F03D1` FOREIGN KEY (`item_fk`) REFERENCES `itemmenu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmenu`
--

LOCK TABLES `itemmenu` WRITE;
/*!40000 ALTER TABLE `itemmenu` DISABLE KEYS */;
INSERT INTO `itemmenu` VALUES (1,'Datos del Sistema',3,'',NULL),(2,'Usuarios',4,'',NULL),(3,'Alta de Usuario',NULL,'/usuario.do?metodo=cargarAltaUsuario',2),(4,'Modificación de Usuario',NULL,'/cargarUsuariosAModificar.do?metodo=cargarUsuariosAModificar',2),(5,'Localidad',NULL,'',1),(6,'Alta de Localidad',NULL,'/jsp.do?page=.altaLocalidad&metodo=altaLocalidad',5),(7,'Modificación de Localidad',NULL,'/cargarLocalidadesAModificar.do?metodo=cargarLocalidadesAModificar',5),(8,'Año de Declaración',NULL,'',1),(9,'Alta de Año de Declaración',NULL,'/jsp.do?page=.altaPeriodo&metodo=altaPeriodo',8),(10,'Modificación de Año de Declaración',NULL,'/cargarPeriodosAModificar.do?metodo=cargarPeriodosAModificar',8),(11,'Salir',5,'',NULL),(12,'Salir de la Aplicación',NULL,'/login.do?metodo=logout',11),(13,'Entidad',NULL,NULL,1),(14,'Alta Entidad',NULL,'/entidad.do?metodo=cargarAltaEntidad',13),(15,'Modificación Entidad',NULL,'/cargarEntidadesAModificar.do?metodo=cargarEntidadesAModificar',13),(16,'Tipo de Producto',NULL,NULL,1),(17,'Modificación de Regalía Minera',NULL,'/recuperarTipoProducto.do?metodo=cargarModificacionTipoProductoForestal',16),(18,'Declaración de Extracción',1,NULL,NULL),(19,'Alta Declaración de Extracción',NULL,'/declaracionExtraccion.do?metodo=cargarAltaDeclaracionExtraccion',18),(20,'Zona de Extracción',NULL,NULL,1),(21,'Alta de Zona de Extracción',NULL,'/localizacion.do?metodo=cargarAltaLocalizacion',20),(22,'Modificación de Zona de Extracción',NULL,'/localizacion.do?metodo=cargarModificacionLocalizacion',20),(23,'Canon Minero',2,NULL,NULL),(24,'Alta Canon Minero',NULL,'/canonMinero.do?metodo=cargarAltaCanonMinero',23),(25,'Alta de Provincia',NULL,'/jsp.do?page=.altaProvincia&metodo=altaProvinciaDestino',5),(26,'Modificación de Provincia',NULL,'/provinciaDestino.do?metodo=cargarProvinciasDestinoAModificar',5),(27,'Alta de Localidad Destino',NULL,'/localidadDestino.do?metodo=cargarAltaLocalidadDestino',5),(28,'Modificación de Localidad Destino',NULL,'/localidadDestino.do?metodo=cargarModificacionLocalidadDestino',5),(29,'Valor Canon Minero',NULL,'',1),(30,'Modificacion Valor Canon Minero',NULL,'/canonMinero.do?metodo=cargarModificacionValorCanonMinero',29);
/*!40000 ALTER TABLE `itemmenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidaddestino`
--

DROP TABLE IF EXISTS `localidaddestino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidaddestino` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `provinciaDestino_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE53A517F1CA9C4B2` (`provinciaDestino_fk`),
  CONSTRAINT `FKE53A517F1CA9C4B2` FOREIGN KEY (`provinciaDestino_fk`) REFERENCES `provinciadestino` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidaddestino`
--

LOCK TABLES `localidaddestino` WRITE;
/*!40000 ALTER TABLE `localidaddestino` DISABLE KEYS */;
INSERT INTO `localidaddestino` VALUES (1,'Rio Gallegos',1),(2,'Rio Turbio',1),(3,'Viedma',2),(4,'Puerto Deseado',1);
/*!40000 ALTER TABLE `localidaddestino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `canonmineroxpertenencia`
--

DROP TABLE IF EXISTS `canonmineroxpertenencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canonmineroxpertenencia` (
  `id` bigint(20) NOT NULL,
  `valor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canonmineroxpertenencia`
--

LOCK TABLES `canonmineroxpertenencia` WRITE;
/*!40000 ALTER TABLE `canonmineroxpertenencia` DISABLE KEYS */;
INSERT INTO `canonmineroxpertenencia` VALUES (1,200);
/*!40000 ALTER TABLE `canonmineroxpertenencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'Rio Grande'),(2,'Ushuaia'),(3,'Tolhuin');
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoproducto`
--

DROP TABLE IF EXISTS `tipoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoproducto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `regaliaMinera` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoproducto`
--

LOCK TABLES `tipoproducto` WRITE;
/*!40000 ALTER TABLE `tipoproducto` DISABLE KEYS */;
INSERT INTO `tipoproducto` VALUES (1,'Turba',55);
/*!40000 ALTER TABLE `tipoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provinciadestino`
--

DROP TABLE IF EXISTS `provinciadestino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provinciadestino` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provinciadestino`
--

LOCK TABLES `provinciadestino` WRITE;
/*!40000 ALTER TABLE `provinciadestino` DISABLE KEYS */;
INSERT INTO `provinciadestino` VALUES (1,'Santa Cruz'),(2,'Rio Negro');
/*!40000 ALTER TABLE `provinciadestino` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-10 15:00:23
