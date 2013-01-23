create database Grupo_Izagar
use Grupo_Izagar

create table tb_establecimiento(
	folio int primary key,
	nombre varchar(20),
	abreviatura char(5),
	status int
)

create table tb_puesto(
	folio int identity primary key,
	nombre varchar(20),
	abreviatura char(5),
	status int
);

create table tb_sueldo(
	folio int identity primary key,
	sueldo float,
	abreviatura char(5),
	status int
);

create table tb_bono(
	folio int identity primary key,
	bono float,
	abreviatura char(5),
	status int
);

-- tabla de rango de prestamos se tiene que crear primero que la del empleado para la llave foranea
-- para agregar la tabla voy a alterar la tabla de empleado para agregar la clave foranea de prestamos
create table tb_rango_prestamos(
	folio int identity primary key,
	minimo money,
	maximo money,
	descuento money,
	status int
)

alter table tb_empleado add rango_prestamo_id int -- para agregar esta columna
alter table tb_empleado add foreign key (rango_prestamo_id) references tb_rango_prestamos(folio) -- agregar el foreing key
select * from tb_empleado where folio=1
create table tb_empleado(
	folio int identity primary key,
	no_checador int,
	nombre varchar(20),
	ap_paterno varchar(20),
	ap_materno varchar(20),
	establecimiento_id int,
	puesto_id int,
	sueldo_id int,
	bono_id int,
	rango_prestamo_id int,
	fuente_sodas int,
	gafete int,
	status int,
	fecha varchar(30),

	foreign key (establecimiento_id) references tb_establecimiento(folio),
	foreign key (rango_prestamo_id) references tb_rango_prestamos(folio), -- agregue el foreign key
	foreign key (puesto_id) references tb_puesto(folio),
	foreign key (sueldo_id) references tb_sueldo(folio),
	foreign key (bono_id) references tb_bono(folio)
	
);


create table tb_permiso(
	folio int primary key identity,
	nombre varchar(50),
	status int
);

create table tb_usuario(
	folio int primary key identity,
	nombre_completo varchar(52),
	contrasena varchar(32),
	permiso_id int,
	fecha varchar(30),
	fecha_actu varchar(30),
	status int,
	foreign key (permiso_id) references tb_permiso(folio)
);

alter table tb_prestamo add abonos money-- para agregar esta columna

create table tb_prestamo(
	folio int primary key identity,
	folio_empleado int, 
	nombre_completo varchar(120),
	fecha varchar(14),
	cantidad money,
	descuento money,
	saldo money,
	abonos int,-- para agregar esta columna
	status int,
	status_descuento int
);

create table tb_fuente_sodas_rh(
	folio int primary key identity,
	ticket varchar(15),
	folio_empleado int ,
	nombre_completo varchar(120),
	cantidad money,
	fecha varchar(14),
	status int
);

EXEC SP_RENAME 'tb_fuente_sodas_auxf.ticket', 'status_ticket'

ALTER TABLE tb_fuente_sodas_auxf  ALTER COLUMN status_ticket char(1)

sp_columns tb_fuente_sodas_auxf

alter table tb_fuente_sodas_auxf insert column(
	folio int primary key identity,
	ticket varchar(15),
	folio_empleado int, 
	nombre_completo varchar(120),
	cantidad money,
	fecha varchar(14),
	status int
);

select tb_empleado.folio as [Folio],
	   tb_empleado.nombre as [Nombre],
	   tb_empleado.ap_paterno as [Paterno],
	   tb_empleado.ap_materno as [Materno],
	   tb_establecimiento.nombre as [Establecimiento],
	   tb_puesto.nombre as [Puesto],
	   tb_sueldo.sueldo as [Sueldo],
	   tb_bono.bono as [Bono],
	   tb_empleado.status as [Status],
	   tb_empleado.fuente_sodas as [Fuentes],
	   tb_empleado.gafete as [Gafete] 

from tb_empleado, tb_establecimiento, tb_puesto, tb_sueldo, tb_bono

where
	  tb_empleado.establecimiento_id = tb_establecimiento.folio and
	  tb_empleado.puesto_id = tb_puesto.folio and
	  tb_empleado.sueldo_id = tb_sueldo.folio and
	  tb_empleado.bono_id = tb_bono.folio


select tb_empleado.folio as [Folio],
	   tb_empleado.nombre as [Nombre],
	   tb_empleado.ap_paterno as [Paterno],
	   tb_empleado.ap_materno as [Materno],
	   tb_establecimiento.nombre as [Establecimiento],
		
	   tb_empleado.status as [Status],
	   tb_rango_prestamos.minimo as [RangoMin],
	   tb_rango_prestamos.maximo as [RangoMax],

	   tb_sueldo.sueldo as [Sueldo] 

from tb_empleado, tb_establecimiento, tb_sueldo, tb_rango_prestamos 

where 
	  tb_empleado.establecimiento_id = tb_establecimiento.folio and
	  tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and
	  tb_empleado.sueldo_id = tb_sueldo.folio and 
	  tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio  and
	  tb_empleado.sueldo_id = tb_sueldo.folio

select * from tb_empleado
select * from tb_sueldo
select * from tb_puesto
select * from tb_establecimiento
select * from tb_bono
select * from tb_prestamo
select * from tb_rango_prestamos
select * from tb_usuario
select * from tb_permiso
select * from tb_fuente_sodas_rh
select * from tb_fuente_sodas_auxf

select minimo,maximo 
from tb_empleado,tb_rango_prestamos 
where 
tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio


select 
		tb_prestamo.folio as [folio],
		tb_prestamo.folio_empleado as [folio_Emp],
		tb_prestamo.nombre_completo as [nombre],
		tb_establecimiento.nombre as [establecimiento],
		tb_prestamo.fecha as [fecha],
		tb_prestamo.cantidad as [prestamo],
		tb_prestamo.descuento as[desc],
		tb_prestamo.saldo as [saldo]
from tb_prestamo,tb_establecimiento,tb_empleado
where tb_prestamo.status='1' 
and tb_prestamo.folio_empleado= tb_empleado.folio and tb_empleado.establecimiento_id = tb_establecimiento.folio

create table tb_deduccion_asistencia(
	folio int primary key identity,
	folio_empleado int,
	establecimiento varchar(20),
	nombre_completo varchar(120),
	puntualidad char(5),
	falta char(5),
	dia_faltas int,
	asistencia char(5),
	status int
)

delete tb_deduccion_asistencia 
select * from tb_deduccion_asistencia;

create table tb_asistencia_puntualidad(
	folio int primary key identity,
	asistencia money,
	puntualidad money
)




select  tb_prestamo.folio_empleado as [Empleado],
		tb_prestamo.fecha as [fecha],
		tb_prestamo.saldo as [saldo],
		tb_prestamo.descuento as [desc],
		tb_prestamo.status_descuento as [statusD]
from    tb_prestamo,tb_empleado
where   tb_prestamo.folio_empleado= tb_empleado.folio

select sum(descuento) 
from tb_prestamo
left join tb_empleado on tb_empleado.folio=tb_prestamo.folio_empleado
where tb_prestamo.folio_empleado=3 and tb_prestamo.status_descuento=0


select (sum(tb_prestamo.cantidad)-sum(tb_prestamo.descuento))
from tb_prestamo
where tb_prestamo.folio_empleado=3 and tb_prestamo.status_descuento=0

select distinct folio_empleado				
from tb_prestamo

select  tb_empleado.folio as [Folio],
		tb_empleado.nombre as [Nombre], 
		tb_empleado.ap_paterno as [Paterno], 
		tb_empleado.ap_materno as [Materno], 
		tb_establecimiento.nombre as [Establecimiento], 
		tb_empleado.status as [Status], 
		ROUND(tb_rango_prestamos.minimo,2) as [RangoMin], 
		ROUND(tb_rango_prestamos.maximo,2) as [RangoMax],

		tb_sueldo.sueldo as [sueldo]					 
		--sum(tb_prestamo.cantidad) as [Cantidad], 
		--sum(tb_prestamo.descuento) as [Descuento] 

from   tb_empleado, tb_establecimiento, tb_sueldo, tb_rango_prestamos

where   tb_empleado.establecimiento_id = tb_establecimiento.folio and
		tb_empleado.status < 3 and tb_empleado.fuente_sodas = '1' and
		tb_empleado.sueldo_id = tb_sueldo.folio and 
		tb_empleado.rango_prestamo_id = tb_rango_prestamos.folio and
		tb_empleado.sueldo_id = tb_sueldo.folio
