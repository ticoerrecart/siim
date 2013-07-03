insert into ItemMenu values(33,'Pago de Boletas de Depósito',null,'/canonMinero.do?metodo=cargarProductoresParaPagoBoletasDeposito',23);

update ItemMenu
set orden = 5
where id= 1;

update ItemMenu
set orden = 6
where id= 2;

update ItemMenu
set orden = 7
where id= 11;

insert into ItemMenu values(34,'Consultas',4,'',null);
insert into ItemMenu values(35,'Canon Minero',null,'',34);
insert into ItemMenu values(36,'Canon Minero',null,'/consultasCanonMinero.do?metodo=cargarProductoresLocalizacionPeriodo',35);

insert into rol_item values(2,33);
insert into rol_item values(2,34);
insert into rol_item values(2,35);
insert into rol_item values(2,36);