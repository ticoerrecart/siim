CREATE DATABASE  IF NOT EXISTS `siim` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `siim`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: siim
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
  PRIMARY KEY (`id`),
  KEY `FK456F1C9C8FC0F36` (`localidad_fk`),
  CONSTRAINT `FK456F1C9C8FC0F36` FOREIGN KEY (`localidad_fk`) REFERENCES `localidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidad`
--

LOCK TABLES `entidad` WRITE;
/*!40000 ALTER TABLE `entidad` DISABLE KEYS */;
INSERT INTO `entidad` VALUES ('PRD',2,1950,'20156983212','Rivera 345','rgonzalez@gmail.com','Roberto Gonzalez',654654,'(0546)456-7899',1),('RN',3,1900,'20111111112','Gomez 987','rminerosrg@gmail.com','Recursos Mineros Rio Grande',654,'65454',1),('RN',4,0,'','qweqw','rminerosu@gmail.com','Recursos Mineros Ushuaia',0,'09809',2);
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
INSERT INTO `rol_item` VALUES (3,2),(3,4),(3,11),(3,12),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (1,'2010-2011'),(2,'2011-2012'),(3,'2012-2013');
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
  `nombre` varchar(255) DEFAULT NULL,
  `entidad_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD95D606AAE3B93B6` (`entidad_fk`),
  CONSTRAINT `FKD95D606AAE3B93B6` FOREIGN KEY (`entidad_fk`) REFERENCES `entidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localizacion`
--

LOCK TABLES `localizacion` WRITE;
/*!40000 ALTER TABLE `localizacion` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmenu`
--

LOCK TABLES `itemmenu` WRITE;
/*!40000 ALTER TABLE `itemmenu` DISABLE KEYS */;
INSERT INTO `itemmenu` VALUES (1,'Datos del Sistema',1,'',NULL),(2,'Usuarios',2,'',NULL),(3,'Alta de Usuario',NULL,'/usuario.do?metodo=cargarAltaUsuario',2),(4,'Modificacion de Usuario',NULL,'/cargarUsuariosAModificar.do?metodo=cargarUsuariosAModificar',2),(5,'Localidad',NULL,'',1),(6,'Alta de Localidad',NULL,'/jsp.do?page=.altaLocalidad&metodo=altaLocalidad',5),(7,'Modificación de Localidad',NULL,'/cargarLocalidadesAModificar.do?metodo=cargarLocalidadesAModificar',5),(8,'Período',NULL,'',1),(9,'Alta de Período',NULL,'/jsp.do?page=.altaPeriodo&metodo=altaPeriodo',8),(10,'Modificación de Período',NULL,'/cargarPeriodosAModificar.do?metodo=cargarPeriodosAModificar',8),(11,'Salir',3,'',NULL),(12,'Salir de la Aplicacion',NULL,'/login.do?metodo=logout',11),(13,'Entidad',NULL,NULL,1),(14,'Alta Entidad',NULL,'/entidad.do?metodo=cargarAltaEntidad',13),(15,'Modificacion Entidad',NULL,'/cargarEntidadesAModificar.do?metodo=cargarEntidadesAModificar',13);
/*!40000 ALTER TABLE `itemmenu` ENABLE KEYS */;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-30 15:18:00
