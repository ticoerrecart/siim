delimiter $$

CREATE TABLE `actadeverificacion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agenteVerificacion` varchar(255) NOT NULL,
  `areaDeVerificacion` varchar(255) DEFAULT NULL,
  `areaFiscalizadora` varchar(255) DEFAULT NULL,
  `bolsaCantidad` int(11) NOT NULL,
  `bolsaObservaciones` varchar(255) DEFAULT NULL,
  `bolsaTitularMembrete` varchar(255) DEFAULT NULL,
  `bolsaVolumenD3` int(11) NOT NULL,
  `domicilioDestinatario` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `fechaVerificacion` datetime DEFAULT NULL,
  `funcionarioActuante` varchar(255) DEFAULT NULL,
  `granelObservaciones` varchar(255) DEFAULT NULL,
  `granelVolumenM3Declarado` int(11) NOT NULL,
  `granelVolumenM3Medido` int(11) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `numeroDeFactura` int(11) NOT NULL,
  `numeroDeRemito` int(11) NOT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `productor_fk` bigint(20) DEFAULT NULL,
  `oficinaMinera_fk` bigint(20) DEFAULT NULL,
  `yacimiento_fk` bigint(20) DEFAULT NULL,
  `localidad_fk` bigint(20) DEFAULT NULL,
  `destino_fk` bigint(20) DEFAULT NULL,
  `transporte_fk` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26DB9EFC609295ED` (`productor_fk`),
  KEY `FK26DB9EFC222FABCE` (`yacimiento_fk`),
  KEY `FK26DB9EFCEC31BD09` (`destino_fk`),
  KEY `FK26DB9EFCC8FC0F36` (`localidad_fk`),
  KEY `FK26DB9EFC77722A92` (`oficinaMinera_fk`),
  KEY `FK26DB9EFCB3D7EFB2` (`transporte_fk`),
  CONSTRAINT `FK26DB9EFCB3D7EFB2` FOREIGN KEY (`transporte_fk`) REFERENCES `transporte` (`id`),
  CONSTRAINT `FK26DB9EFC222FABCE` FOREIGN KEY (`yacimiento_fk`) REFERENCES `localizacion` (`id`),
  CONSTRAINT `FK26DB9EFC609295ED` FOREIGN KEY (`productor_fk`) REFERENCES `entidad` (`id`),
  CONSTRAINT `FK26DB9EFC77722A92` FOREIGN KEY (`oficinaMinera_fk`) REFERENCES `localidad` (`id`),
  CONSTRAINT `FK26DB9EFCC8FC0F36` FOREIGN KEY (`localidad_fk`) REFERENCES `localidad` (`id`),
  CONSTRAINT `FK26DB9EFCEC31BD09` FOREIGN KEY (`destino_fk`) REFERENCES `localidaddestino` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;



CREATE TABLE `transporte` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dominio` varchar(255) NOT NULL,
  `empresaDePesaje` varchar(255) DEFAULT NULL,
  `empresaDeTransporte` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `nombreChofer` varchar(255) DEFAULT NULL,
  `pesoBrutoEnKilos` int(11) NOT NULL,
  `pesoNetoEnKilos` int(11) NOT NULL,
  `semirremolqueDominio` varchar(255) DEFAULT NULL,
  `semirremolqueMarca` varchar(255) DEFAULT NULL,
  `taraEnKilos` int(11) NOT NULL,
  `ticketBalanza` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1$$






INSERT INTO `x071vm20_siim`.`itemmenu`(`id`,`item`,`orden`,`url`,`item_fk`) 
VALUES (31,'Acta de Verificación',,'',null);
INSERT INTO `x071vm20_siim`.`itemmenu`(`id`,`item`,`orden`,`url`,`item_fk`) 
VALUES (32,'Alta Acta de Verificación',null,'\actaDeVerificacion.do?metodo=cargarActaDeVerificacion',31);

UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='3' WHERE `id`='23';
UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='6' WHERE `id`='11';
UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='5' WHERE `id`='2';
UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='4' WHERE `id`='1';

INSERT INTO `x071vm20_siim`.`rol_item` (`rol_fk`,`item_fk`) VALUES (2,31);
INSERT INTO `x071vm20_siim`.`rol_item` (`rol_fk`,`item_fk`) VALUES (2,32);
