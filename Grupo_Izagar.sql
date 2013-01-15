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
	rango_prestamo_id int, -- agregue esta columna
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
select * from tb_empleado
select * from tb_fuente_sodas_rh
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

select * from tb_fuente_sodas_auxf
alter table tb_fuente_sodas_auxf insert column(
	folio int primary key identity,
	ticket varchar(15),
	folio_empleado int, 
	nombre_completo varchar(120),
	cantidad money,
	fecha varchar(14),
	status int
);


-- Tabla de Deducci�n de Asistencia

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