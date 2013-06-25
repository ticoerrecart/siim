INSERT INTO `x071vm20_siim`.`itemmenu`(`id`,`item`,`orden`,`url`,`item_fk`) VALUES (
31,
'Acta de Verificación',
2,
'',
null);
INSERT INTO `x071vm20_siim`.`itemmenu`(`id`,`item`,`orden`,`url`,`item_fk`) VALUES (
32,
'Alta Acta de Verificación',
null,
'\actaDeVerificacion.do?metodo=cargarActaDeVerificacion',
31);

UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='3' WHERE `id`='23';

UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='6' WHERE `id`='11';

UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='5' WHERE `id`='2';

UPDATE `x071vm20_siim`.`itemmenu` SET `orden`='4' WHERE `id`='1';

INSERT INTO `x071vm20_siim`.`rol_item` (`rol_fk`,`item_fk`) VALUES (2,31);
INSERT INTO `x071vm20_siim`.`rol_item` (`rol_fk`,`item_fk`) VALUES (2,32);
