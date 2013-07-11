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
-- Table structure for table `estudioimpactoambiental`
--

DROP TABLE IF EXISTS `estudioimpactoambiental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estudioimpactoambiental` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) NOT NULL,
  `fechaDesde` datetime DEFAULT NULL,
  `fechaHasta` datetime DEFAULT NULL,
  `nroResolucionEIA` varchar(255) DEFAULT NULL,
  `localizacion_fk` bigint(20) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `vigente` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEBF393558C874572` (`localizacion_fk`),
  CONSTRAINT `FKEBF393558C874572` FOREIGN KEY (`localizacion_fk`) REFERENCES `localizacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


