ALTER TABLE `x071vm20_siim`.`periodo` ADD COLUMN `fechaVencimientoCuartoTrimestre` DATETIME NOT NULL AFTER `periodo` , ADD COLUMN `fechaVencimientoPrimerTrimestre` DATETIME NOT NULL  AFTER `periodo` , ADD COLUMN `fechaVencimientoSegundoTrimestre` DATETIME NOT NULL  AFTER `fechaVencimientoPrimerTrimestre` , ADD COLUMN `fechaVencimientoTercerTrimestre` DATETIME NOT NULL  AFTER `fechaVencimientoSegundoTrimestre` ;

UPDATE `x071vm20_siim`.`periodo` SET `fechaVencimientoPrimerTrimestre`='2010-03-30 00:00:00', `fechaVencimientoSegundoTrimestre`='2010-06-30 00:00:00', `fechaVencimientoTercerTrimestre`='2010-09-30 00:00:00', `fechaVencimientoCuartoTrimestre`='2010-12-31 00:00:00' WHERE `id`='1';

UPDATE `x071vm20_siim`.`periodo` SET `fechaVencimientoPrimerTrimestre`='2011-03-30 00:00:00', `fechaVencimientoSegundoTrimestre`='2011-06-30 00:00:00', `fechaVencimientoTercerTrimestre`='2011-09-30 00:00:00', `fechaVencimientoCuartoTrimestre`='2011-12-31 00:00:00' WHERE `id`='2';

UPDATE `x071vm20_siim`.`periodo` SET `fechaVencimientoPrimerTrimestre`='2012-03-30 00:00:00', `fechaVencimientoSegundoTrimestre`='2012-06-30 00:00:00', `fechaVencimientoTercerTrimestre`='2012-09-30 00:00:00', `fechaVencimientoCuartoTrimestre`='2012-12-31 00:00:00' WHERE `id`='3';

UPDATE `x071vm20_siim`.`periodo` SET `fechaVencimientoPrimerTrimestre`='2014-03-30 00:00:00', `fechaVencimientoSegundoTrimestre`='2014-06-30 00:00:00', `fechaVencimientoTercerTrimestre`='2014-09-30 00:00:00', `fechaVencimientoCuartoTrimestre`='2014-12-31 00:00:00' WHERE `id`='4';

UPDATE `x071vm20_siim`.`periodo` SET `fechaVencimientoPrimerTrimestre`='2013-03-30 00:00:00', `fechaVencimientoSegundoTrimestre`='2013-06-30 00:00:00', `fechaVencimientoTercerTrimestre`='2013-09-30 00:00:00', `fechaVencimientoCuartoTrimestre`='2013-12-31 00:00:00' WHERE `id`='5';

