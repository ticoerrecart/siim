ALTER TABLE `x071vm20_siim`.`BoletaDeposito` ADD COLUMN `volumenDeclaracionDeExtraccion_fk` BIGINT(20);

CREATE TABLE `TrimestreDeclaracionDeExtraccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fechaVencimiento` datetime DEFAULT NULL,
  `nroTrimestre` int(11) DEFAULT NULL,
  `volumenPrimerMes` double DEFAULT NULL,
  `volumenSegundoMes` double DEFAULT NULL,
  `volumenTercerMes` double DEFAULT NULL,
  `tipoProducto_fk` bigint(20) DEFAULT NULL,
  `volumenDeclaracionDeExtraccion_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14EF328DBA30CD52` (`tipoProducto_fk`),
  KEY `FK14EF328DDF5C52` (`volumenDeclaracionDeExtraccion_fk`),
  CONSTRAINT `FK14EF328DBA30CD52` FOREIGN KEY (`tipoProducto_fk`) REFERENCES `tipoproducto` (`id`),
  CONSTRAINT `FK14EF328DDF5C52` FOREIGN KEY (`volumenDeclaracionDeExtraccion_fk`) REFERENCES `volumendeclaraciondeextraccion` (`id`)
);

CREATE TABLE `VolumenDeclaracionDeExtraccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `declaracionDeExtraccion_fk` bigint(20) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C4248B0A741F616` (`declaracionDeExtraccion_fk`),
  CONSTRAINT `FK7C4248B0A741F616` FOREIGN KEY (`declaracionDeExtraccion_fk`) REFERENCES `declaraciondeextraccion` (`id`)
);



CREATE TABLE `DeclaracionDeExtraccion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anulado` bit(1) NOT NULL,
  `fecha` varchar(255) DEFAULT NULL,
  `importeTotal` double NOT NULL,
  `numero` bigint(20) NOT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `localizacion_fk` bigint(20) DEFAULT NULL,
  `localidad_fk` bigint(20) DEFAULT NULL,
  `productor_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK551E9FA4609295ED` (`productor_fk`),
  KEY `FK551E9FA4C8FC0F36` (`localidad_fk`),
  KEY `FK551E9FA48C874572` (`localizacion_fk`),
  CONSTRAINT `FK551E9FA4609295ED` FOREIGN KEY (`productor_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK551E9FA48C874572` FOREIGN KEY (`localizacion_fk`) REFERENCES `localizacion` (`id`),
  CONSTRAINT `FK551E9FA4C8FC0F36` FOREIGN KEY (`localidad_fk`) REFERENCES `localidad` (`id`)
);