package SQL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;

import objetos.Obj_Actividad;
import objetos.Obj_Alimentacion_Cortes;
import objetos.Obj_Alimentacion_Denominacion;
import objetos.Obj_Asistencia_Puntualidad;
import objetos.Obj_Atributos;
import objetos.Obj_Auto_Auditoria;
import objetos.Obj_Auto_Finanzas;
import objetos.Obj_Bancos;
import objetos.Obj_Bono_Complemento_Sueldo;
import objetos.Obj_Conexion_BD;
import objetos.Obj_Configuracion_Sistema;
import objetos.Obj_Cuadrante;
import objetos.Obj_Deduccion_Iasistencia;
import objetos.Obj_Denominaciones;
import objetos.Obj_Divisa_Y_TipoDeCambio;
import objetos.Obj_Diferencia_Cortes;
import objetos.Obj_Empleado;
import objetos.Obj_Equipo_Trabajo;
import objetos.Obj_Establecimiento;
import objetos.Obj_Jefatura;
import objetos.Obj_Nivel_Critico;
import objetos.Obj_OpRespuesta;
import objetos.Obj_Persecciones_Extra;
import objetos.Obj_Ponderacion;
import objetos.Obj_Revision_Lista_Raya;
import objetos.Obj_Prestamo;
import objetos.Obj_Puesto;
import objetos.Obj_Rango_Prestamos;
import objetos.Obj_Sueldo;
import objetos.Obj_Temporada;
import objetos.Obj_Tipo_Banco;
import objetos.Obj_Turno;
import objetos.Obj_Usuario;
import objetos.Obj_Usuario3;
import objetos.Obj_fuente_sodas_auxf;
import objetos.Obj_fuente_sodas_rh;

public class BuscarSQL {
	
	Connexion con = new Connexion();
	
	public Obj_Establecimiento Establecimiento(int folio) throws SQLException{
		Obj_Establecimiento establecimiento = new Obj_Establecimiento();
		String query = "select * from tb_establecimiento where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				establecimiento.setFolio(rs.getInt("folio"));
				establecimiento.setNombre(rs.getString("nombre").trim());
				establecimiento.setAbreviatura(rs.getString("abreviatura").trim());
				establecimiento.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return establecimiento;
	}
	
	public Obj_Establecimiento Establecimiento_Nuevo() throws SQLException{
		Obj_Establecimiento establecimiento = new Obj_Establecimiento();
		String query = "select max(folio) as 'Maximo' from tb_establecimiento";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				establecimiento.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return establecimiento;
	}
	
	public Obj_Sueldo Sueldo(int folio) throws SQLException{
		Obj_Sueldo sueldo = new Obj_Sueldo();
		String query = "select * from tb_sueldo where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				sueldo.setFolio(rs.getInt("folio"));
				sueldo.setSueldo(rs.getFloat("sueldo"));
				sueldo.setPuesto(rs.getInt("puesto_id"));
				sueldo.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt != null){stmt.close();}
		}
		return sueldo;
	}

	public Obj_Sueldo Sueldo_Nuevo() throws SQLException{
		Obj_Sueldo sueldo = new Obj_Sueldo();
		String query = "select max(folio) as 'Maximo' from tb_sueldo";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				sueldo.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return sueldo;
	}
	
	public Obj_Bono_Complemento_Sueldo Bono(int folio) throws SQLException{
		Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo();
		String query = "select * from tb_bono where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("folio"));
				bono.setBono(rs.getFloat("bono"));
				bono.setAbreviatura(rs.getString("abreviatura").trim());
				bono.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Bono_Complemento_Sueldo BonoValor(float valor) throws SQLException{
		Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo();
		String query = "select * from tb_bono where bono = "+ valor;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("folio"));
				bono.setBono(rs.getFloat("bono"));
				bono.setAbreviatura(rs.getString("abreviatura").trim());
				bono.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Bono_Complemento_Sueldo Bono_Nuevo() throws SQLException{
		Obj_Bono_Complemento_Sueldo bono = new Obj_Bono_Complemento_Sueldo();
		String query = "select max(folio) as 'Maximo' from tb_bono";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Puesto Puesto(int folio) throws SQLException{
		Obj_Puesto puesto = new Obj_Puesto();
		String query = "select * from tb_puesto where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				puesto.setFolio(rs.getInt("folio"));
				puesto.setPuesto(rs.getString("nombre").trim());
				puesto.setAbreviatura(rs.getString("abreviatura").trim());
				puesto.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return puesto;
	}
	
	public Obj_Atributos Atributos(int folio) throws SQLException{
		Obj_Atributos atrib = new Obj_Atributos();
		String query = "select * from tb_atributo where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				atrib.setFolio(rs.getInt("folio"));
				atrib.setDescripcion(rs.getString("Descripcion").trim());
				atrib.setValor(rs.getFloat("Valor"));
				atrib.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return atrib;
	}
	
	public Obj_Jefatura Jefatura(int folio) throws SQLException{
		Obj_Jefatura jefat = new Obj_Jefatura();
		String query = "select * from tb_jefatura where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				jefat.setFolio(rs.getInt("folio"));
				jefat.setDescripcion(rs.getString("Descripcion").trim());
				jefat.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return jefat;
	}
	
	public Obj_Equipo_Trabajo Eq_Tabajo(int folio) throws SQLException{
		Obj_Equipo_Trabajo EqTrabajo = new Obj_Equipo_Trabajo();
		String query = "select * from tb_equipo_trabajo where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				EqTrabajo.setFolio(rs.getInt("folio"));
				EqTrabajo.setDescripcion(rs.getString("Descripcion").trim());
				EqTrabajo.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return EqTrabajo;
	}
	
	public Obj_Ponderacion Ponderacion(int folio) throws SQLException{
		Obj_Ponderacion pond = new Obj_Ponderacion();
		String query = "select * from tb_ponderacion where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				pond.setFolio(rs.getInt("folio"));
				pond.setDescripcion(rs.getString("Descripcion").trim());
				pond.setValor(rs.getFloat("Valor"));
				pond.setFechaIn(rs.getString("fecha_In").trim());
				pond.setFechaFin(rs.getString("fecha_fin").trim());
				pond.setDia(rs.getInt("dia"));
				pond.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return pond;
	}
	
	public Obj_Nivel_Critico Nivel(int folio) throws SQLException{
		Obj_Nivel_Critico nc = new Obj_Nivel_Critico();
		String query = "select * from tb_nivel_critico where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				nc.setFolio(rs.getInt("folio"));
				nc.setDescripcion(rs.getString("Descripcion").trim());
				nc.setValor(rs.getInt("Valor"));
				nc.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return nc;
	}
	
	public Obj_Cuadrante Cuadrante(int folio) throws SQLException{
		Obj_Cuadrante cuadrante = new Obj_Cuadrante();
		String query = "select * from tb_cuadrante where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				cuadrante.setFolio(rs.getInt("folio"));
				cuadrante.setNombre(rs.getString("nombre").trim());
				cuadrante.setEstablecimiento(rs.getInt("establecimiento_id"));
				cuadrante.setNivel_gerarquico(rs.getInt("nivel_gerarquico"));
				cuadrante.setDia(rs.getInt("dia"));
				cuadrante.setEq_trabajo(rs.getInt("equipo_trabajo"));
				cuadrante.setJefatura(rs.getInt("jefatura"));
				cuadrante.setStatus((rs.getString("status").equals("1"))?true:false);
				cuadrante.setDescripcion(rs.getString("descripcion"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return cuadrante;
	}
	
	public Obj_OpRespuesta OpRespuesta(int folio) throws SQLException{
		Obj_OpRespuesta opR = new Obj_OpRespuesta();
		String query = "select * from tb_op_respuesta where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				opR.setFolio(rs.getInt("folio"));
				opR.setDescripcion(rs.getString("Descripcion").trim());
				opR.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return opR;
	}
	
	public Obj_Tipo_Banco Tipo_Banco(int folio) throws SQLException{
		Obj_Tipo_Banco banck = new Obj_Tipo_Banco();
		String query = "select * from tb_tipo_banco where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				banck.setFolio(rs.getInt("folio"));
				banck.setBanco(rs.getString("nombre").trim());
				banck.setAbreviatura(rs.getString("abreviatura").trim());
				banck.setStatus((rs.getString("status").equals("1"))?true:false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return banck;
	}
		

	public Obj_Alimentacion_Cortes corte(int folio) throws SQLException{
		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
		String query = "select * from tb_alimentacion_cortes where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				corte.setFolio_corte(rs.getInt("folio_corte"));
				corte.setFolio_corte(rs.getInt("folio_empleado"));
				corte.setPuesto(rs.getString("nombre_empleado").trim());
				corte.setPuesto(rs.getString("puesto").trim());
				corte.setEstablecimiento(rs.getString("establecimiento").trim());
				corte.setAsignacion(rs.getString("asignacion").trim());
				corte.setCorte_sistema(rs.getFloat("corte_del_sistema"));
				corte.setDeposito(rs.getFloat("deposito"));
				corte.setEfectivo(rs.getFloat("efectivo"));
				corte.setDiferencia_corte(rs.getFloat("diferencia_corte"));
				corte.setFecha(rs.getString("fecha").trim());
				corte.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return corte;
	}
	
	public Obj_Alimentacion_Cortes Corte(String asignacion) throws SQLException{
		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
		String query = "select * from tb_alimentacion_denominaciones where asignacion ="+ asignacion;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				corte.setFolio_corte(rs.getInt("folio_corte"));
				corte.setFolio_corte(rs.getInt("folio_empleado"));
				corte.setPuesto(rs.getString("nombre_empleado").trim());
				corte.setPuesto(rs.getString("puesto").trim());
				corte.setEstablecimiento(rs.getString("establecimiento").trim());
				corte.setAsignacion(rs.getString("asignacion").trim());
				corte.setCorte_sistema(rs.getFloat("corte_del_sistema"));
				corte.setDeposito(rs.getFloat("deposito"));
				corte.setEfectivo(rs.getFloat("efectivo"));
				corte.setDiferencia_corte(rs.getFloat("diferencia_corte"));
				corte.setFecha(rs.getString("fecha").trim());
				corte.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return corte;
	}
	
	public Obj_Divisa_Y_TipoDeCambio divisas(int folio) throws SQLException{
		Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
		String query = "select * from tb_divisas_tipo_de_cambio where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				divisas.setFolio(rs.getInt("folio"));
				divisas.setNombre(rs.getString("nombre_divisas").trim());
				divisas.setValor(rs.getFloat("valor"));
				divisas.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return divisas;
	}
	
	public Obj_Denominaciones denominaciones(int folio) throws SQLException{
		Obj_Denominaciones denominaciones = new Obj_Denominaciones();
		String query = "select * from tb_denominaciones where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				denominaciones.setFolio(rs.getInt("folio"));
				denominaciones.setNombre(rs.getString("nombre").trim());
				denominaciones.setMoneda(rs.getString("moneda"));
				denominaciones.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return denominaciones;
	}
	
	public Obj_Puesto Puesto_Nuevo() throws SQLException{
		Obj_Puesto puesto = new Obj_Puesto();
		String query = "select max(folio) as 'Maximo' from tb_puesto";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				puesto.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return puesto;
	}
	
	public Obj_Atributos Atributo_Nuevo() throws SQLException{
		Obj_Atributos atrib = new Obj_Atributos();
		String query = "select max(folio) as 'Maximo' from tb_atributo";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				atrib.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return atrib;
	}
	
	public Obj_Jefatura Jefatura_Nuevo() throws SQLException{
		Obj_Jefatura jefat = new Obj_Jefatura();
		String query = "select max(folio) as 'Maximo' from tb_jefatura";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				jefat.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return jefat;
	}
	
	public Obj_Equipo_Trabajo Eq_Trabajo_Nuevo() throws SQLException{
		Obj_Equipo_Trabajo EqTrajajo = new Obj_Equipo_Trabajo();
		String query = "select max(folio) as 'Maximo' from tb_equipo_trabajo";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				EqTrajajo.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return EqTrajajo;
	}
	
	public Obj_Ponderacion Ponderacion_Nuevo() throws SQLException{
		Obj_Ponderacion pond = new Obj_Ponderacion();
		String query = "select max(folio) as 'Maximo' from tb_ponderacion";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				pond.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return pond;
	}
	
	public Obj_Nivel_Critico Nivel_Nuevo() throws SQLException{
		Obj_Nivel_Critico nc = new Obj_Nivel_Critico();
		String query = "select max(folio) as 'Maximo' from tb_nivel_critico";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				nc.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return nc;
	}
	
	public Obj_Cuadrante Cuadrante_Nuevo() throws SQLException{
		Obj_Cuadrante cuadrante = new Obj_Cuadrante();
		String query = "select max(folio) as 'Maximo' from tb_cuadrante";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				cuadrante.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return cuadrante;
	}
	
//	public Obj_Actividad Actividad_Nuevo() throws SQLException{
//		Obj_Actividad actividad = new Obj_Actividad();
//		String query = "select max(folio) as 'Maximo' from tb_actividad";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()){
//				actividad.setFolio(rs.getInt("Maximo"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally{
//			if(stmt!=null){stmt.close();}
//		}
//		return actividad;
//	}
	
	public Obj_OpRespuesta OpRespuesta_Nuevo() throws SQLException{
		Obj_OpRespuesta opR = new Obj_OpRespuesta();
		String query = "select max(folio) as 'Maximo' from tb_op_respuesta";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				opR.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return opR;
	}
	
	public Obj_Tipo_Banco Tipo_Banco_Nuevo() throws SQLException{
		Obj_Tipo_Banco banco = new Obj_Tipo_Banco();
		String query = "select max(folio) as 'Maximo' from tb_tipo_banco";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				banco.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return banco;
	}
		
		
	public Obj_Divisa_Y_TipoDeCambio Divisas_Nuevo() throws SQLException{
		Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
		String query = "select max(folio) as 'Maximo' from tb_divisas_tipo_de_cambio";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				divisas.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return divisas;
	}
	
	public Obj_Denominaciones Denominaciones_Nuevo() throws SQLException{
		Obj_Denominaciones denominaciones = new Obj_Denominaciones();
		String query = "select max(folio) as 'Maximo' from tb_denominaciones";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				denominaciones.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return denominaciones;
	}
	
	public Obj_Empleado Empleado(int folio) throws SQLException{
		Obj_Empleado empleado = new Obj_Empleado();
		String query = "select * from tb_empleado where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				empleado.setFolio(rs.getInt("folio"));
				empleado.setNo_checador(rs.getInt("no_checador"));
				empleado.setNombre(rs.getString("nombre").trim());
				empleado.setAp_paterno(rs.getString("ap_paterno").trim());
				empleado.setAp_materno(rs.getString("ap_materno").trim());
				empleado.setEstablecimiento(rs.getInt("establecimiento_id"));
				empleado.setPuesto(rs.getInt("puesto_id"));
				empleado.setTurno(rs.getInt("turno_id"));
				empleado.setDescanso(rs.getInt("descanso"));
				empleado.setDobla(rs.getInt("dia_dobla"));
				empleado.setSueldo(rs.getInt("sueldo_id"));				
				empleado.setBono(rs.getInt("bono_id"));
				empleado.setPrestamo(rs.getInt("rango_prestamo_id"));
				empleado.setPension_alimenticia(rs.getFloat("pension_alimenticia"));
				empleado.setInfonavit(rs.getFloat("infonavit"));
				empleado.setTargeta_nomina(rs.getString("targeta_nomina"));
				empleado.setTipo_banco(rs.getInt("tipo_banco_id"));
				empleado.setFuente_sodas(rs.getBoolean("fuente_sodas") ? true : false);
				empleado.setGafete(rs.getBoolean("gafete") ? true : false);
				empleado.setStatus(rs.getInt("status"));
				empleado.setFecha(rs.getString("fecha"));
				empleado.setObservasiones(rs.getString("observaciones"));
				empleado.setFoto(rs.getString("foto"));
				empleado.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return empleado;
	}
	
	public Obj_Alimentacion_Denominacion Denom(String asignacion) throws SQLException{
		Obj_Alimentacion_Denominacion denom = new Obj_Alimentacion_Denominacion();
		String query = "select * from tb_alimentacion_denominaciones where asignacion ='"+ asignacion+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				denom.setAsignacion(rs.getString("asignacion").trim());
				denom.setFolio_empleado(rs.getInt("folio_empleado"));
				denom.setFolio_denominacion(rs.getInt("folio_denominacion"));
				denom.setDenominacion(rs.getString("denominacion").trim());
				denom.setValor(rs.getFloat("valor"));
				denom.setCantidad(rs.getFloat("cantidad"));
				denom.setFecha(rs.getString("fecha").trim());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return denom;
	}
	
	public Obj_Revision_Lista_Raya ListaR(int numero_lista) throws SQLException{
		Obj_Revision_Lista_Raya LR = new Obj_Revision_Lista_Raya();
		String query = "select * from tb_lista_raya where numero_lista ="+ numero_lista;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				LR.setNumero_lista(rs.getInt("numero_lista"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return LR;
	}
	
	public Obj_Prestamo Prestamo(int folio) throws SQLException{
		Obj_Prestamo pre = new Obj_Prestamo();
		String query = "select * from tb_prestamo where folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				pre.setFolio(rs.getInt("folio"));
				pre.setFolio(rs.getInt("folio_empleado"));
				pre.setNombre_Completo(rs.getString("nombre_completo").trim());
				pre.setFecha(rs.getString("fecha"));
				pre.setCantidad(rs.getDouble("cantidad"));
				pre.setDescuento(rs.getDouble("descuento"));
				pre.setSaldo(rs.getDouble("saldo"));
				pre.setAbonos(rs.getInt("abonos"));
				pre.setStatus(rs.getInt("status"));
				pre.setStatus(rs.getInt("status_descuento"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return pre;
	}
	
	public Obj_Empleado Empleado_Nuevo() throws SQLException{
		Obj_Empleado empleado = new Obj_Empleado();
		String query = "select max(folio) as 'Maximo' from tb_empleado";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				empleado.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return empleado;
	}
	
	public Obj_Alimentacion_Cortes Corte_Nuevo() throws SQLException{
		Obj_Alimentacion_Cortes corte = new Obj_Alimentacion_Cortes();
		String query = "select max(folio_corte) as 'Maximo' from tb_alimentacion_cortes";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				corte.setFolio_corte(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return corte;
	}
	
	public Obj_Usuario Usuario(int folio) throws SQLException{
		Obj_Usuario usuario = new Obj_Usuario();
		String query = "select * from tb_usuario where folio ="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){			
				usuario.setFolio(rs.getInt("folio"));
				usuario.setNombre_completo(rs.getString("nombre_completo").trim());
				usuario.setContrasena(rs.getString("contrasena").trim());
				usuario.setPermiso_id(rs.getInt("permiso_id"));
				usuario.setFecha_alta(rs.getString("fecha").trim());
				usuario.setStatus(rs.getInt("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return usuario;
	}
	
	public Obj_Usuario3 BuscarUsuarios(String Nombre_Completo) throws SQLException{
		Obj_Usuario3 usuario = new Obj_Usuario3();
		String query = "select * from tb_usuario where nombre_completo ='"+Nombre_Completo+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){			
				usuario.setFolio(rs.getInt("folio"));
				usuario.setNombre_completo(rs.getString("nombre_completo").trim());
				usuario.setContrasena(rs.getString("contrasena").trim());
				usuario.setFecha_alta(rs.getString("fecha").trim());
				usuario.setFecha_alta(rs.getString("fecha_actu").trim());
				usuario.setSesion(rs.getString("sesion"));
				usuario.setStatus(rs.getInt("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return usuario;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector returnPermiso(String Nombre_Completo, int menu) throws SQLException{
		Vector prueba = new Vector();
		String query = "exec sp_get_permisos '"+ Nombre_Completo +"', "+menu ;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){			
				prueba.add(rs.getBoolean(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return prueba;
	}
	
	public boolean existeUsuario(String Nombre_Completo) throws SQLException{
		boolean existe;
		int filas = getFilas("select * from tb_permisos where nombre_completo ='"+Nombre_Completo+"'");
		if(filas > 1){
			existe = true;
		}else{
			existe = false;
		}
		return existe;
	}
	
	
	public Obj_Usuario Maximo() throws SQLException{
		Obj_Usuario usuario = new Obj_Usuario();
		String query = "select max(folio) as 'Maximo' from tb_usuario";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				usuario.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return usuario;
	}
	
	public Obj_fuente_sodas_rh MaximoFuente() throws SQLException{
		Obj_fuente_sodas_rh bono = new Obj_fuente_sodas_rh();
		String query = "select max(folio) as 'Maximo' from tb_fuente_sodas_rh";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_fuente_sodas_auxf MaximoFuente_auxf() throws SQLException{
		Obj_fuente_sodas_auxf bono = new Obj_fuente_sodas_auxf();
		String query = "select max(folio) as 'Maximo' from tb_fuente_sodas_auxf";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Prestamo maximoPrestamo() throws SQLException{
		Obj_Prestamo bono = new Obj_Prestamo();
		String query = "select max(folio) as 'Maximo' from tb_prestamo";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Rango_Prestamos Rango_Prestamos(int folio) throws SQLException{
		Obj_Rango_Prestamos prestamos = new Obj_Rango_Prestamos();
		String query = "select * from tb_rango_prestamos where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				prestamos.setFolio(rs.getInt("folio"));
				prestamos.setPrestamo_minimo(rs.getDouble("minimo"));
				prestamos.setPrestamo_maximo(rs.getDouble("maximo"));
				prestamos.setDescuento(rs.getDouble("descuento"));
				prestamos.setStatus((rs.getString("status").equals("1"))?true:false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return prestamos;
	}
	
	public Obj_Rango_Prestamos Rango_Prestamos_Nuevo() throws SQLException{
		Obj_Rango_Prestamos rango_nuevo = new Obj_Rango_Prestamos();
		String query = "select max(folio) as 'Maximo' from tb_rango_prestamos";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				rango_nuevo.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return rango_nuevo;
	}
	
	public Obj_Asistencia_Puntualidad Asistencia_Puntualidad() throws SQLException{
		Obj_Asistencia_Puntualidad numero = new Obj_Asistencia_Puntualidad();
		String query = "select max(folio) as 'Maximo' from tb_asistencia_puntualidad";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				numero.setExiste(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return numero;
	}
	
	public Obj_Asistencia_Puntualidad Asistencia_Puntualidad(int folio) throws SQLException{
		Obj_Asistencia_Puntualidad numero = new Obj_Asistencia_Puntualidad();
		String query = "select * from tb_asistencia_puntualidad where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				numero.setValorAsistencia(rs.getFloat("asistencia"));
				numero.setValorPuntualidad(rs.getFloat("puntualidad"));
				numero.setValorGafete(rs.getFloat("gafete"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return numero;
	}
	
	public Obj_fuente_sodas_rh fuente_sodas_rh(int folio) throws SQLException {
		Obj_fuente_sodas_rh fuente_sodas = new Obj_fuente_sodas_rh();
		String query = "select status_ticket from tb_fuente_sodas_rh where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				fuente_sodas.setStatus_ticket(rs.getInt("status_ticket"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return fuente_sodas;
	}
	
	public Obj_fuente_sodas_auxf fuente_sodas_ax(int folio) throws SQLException{
		Obj_fuente_sodas_auxf fuente_sodas = new Obj_fuente_sodas_auxf();
		String query = "select status_ticket from tb_fuente_sodas_auxf where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				fuente_sodas.setStatus_ticket(rs.getInt("status_ticket"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return fuente_sodas;
	}
	
	public Obj_Deduccion_Iasistencia Deduccion(int folio) throws SQLException{
		Obj_Deduccion_Iasistencia deduccion = new Obj_Deduccion_Iasistencia();
		String query = "select puntualidad,falta,asistencia,extra from tb_deduccion_asistencia where folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				deduccion.setPuntualidad(rs.getString("puntualidad"));
				deduccion.setFalta(rs.getString("falta"));
				deduccion.setAsistencia(rs.getString("asistencia"));
				deduccion.setExtra(rs.getFloat("extra"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return deduccion;
	}
	
	public Obj_Diferencia_Cortes maximo_diferencia_cortes() throws SQLException{
		Obj_Diferencia_Cortes bono = new Obj_Diferencia_Cortes();
		String query = "select max(folio) as 'Maximo' from tb_prestamo";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				bono.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return bono;
	}
	
	public Obj_Turno Turno(int folio) throws SQLException{
		Obj_Turno turno = new Obj_Turno();
		String query = "select * from tb_turno where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				turno.setFolio(rs.getInt("folio"));
				turno.setNombre(rs.getString("nombre").trim());
				turno.setHorario(rs.getString("horario").trim());
				turno.setStatus(rs.getBoolean("status"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt != null){stmt.close();}
		}
		return turno;
	}
	
	public Obj_Turno Turno_Nuevo() throws SQLException{
		Obj_Turno turno = new Obj_Turno();
		String query = "select max(folio) as 'Maximo' from tb_turno";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				turno.setFolio(rs.getInt("Maximo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return turno;
	}
	
	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public Obj_Conexion_BD Conexion_BD() throws IOException {
		Vector myVector = new Vector();
		Obj_Conexion_BD config = new Obj_Conexion_BD();
		
		try{
			FileReader archivo = new FileReader(System.getProperty("user.dir")+"\\Config\\config");
			BufferedReader bufferedWriter = new BufferedReader(archivo);
			String cadena = "";
			while( (cadena = bufferedWriter.readLine()) !=null)
				myVector.addElement(cadena);
				
				config.setDireccionIPV4(myVector.get(0).toString());
				config.setNombreBD(myVector.get(1).toString());
				config.setUsuario(myVector.get(2).toString());
				config.setContrasena(myVector.get(3).toString());
				
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			return config=null;
		}
		return config;
			
	}
	
	public Obj_Establecimiento Establ_buscar(String nombre) throws SQLException{
		Obj_Establecimiento estab = new Obj_Establecimiento();
		String query = "select folio from tb_establecimiento where nombre='"+nombre+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				estab.setFolio(rs.getInt("folio"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return estab;
	}
	
	public Obj_Puesto Pues_buscar(String nombre) throws SQLException{
		Obj_Puesto puest = new Obj_Puesto();
		String query = "select folio from tb_puesto where nombre='"+nombre+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				puest.setFolio(rs.getInt("folio"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return puest;
	}
	
	public Obj_Tipo_Banco Banco_Buscar(String nombre) throws SQLException{
		Obj_Tipo_Banco banck = new Obj_Tipo_Banco();
		String query = "select folio from tb_tipo_banco where nombre='"+nombre+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				banck.setFolio(rs.getInt("folio"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return banck;
	}
		
	public Obj_Divisa_Y_TipoDeCambio Divisas_buscar(String nombre) throws SQLException{
		Obj_Divisa_Y_TipoDeCambio divisas = new Obj_Divisa_Y_TipoDeCambio();
		String query = "select valor,nombre_divisas from tb_divisas_tipo_de_cambio where nombre_divisas='"+nombre+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				divisas.setNombre(rs.getString("nombre_divisas"));
				divisas.setValor(rs.getFloat("valor"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return divisas;
	}
	
		
//	public Obj_Denominaciones Denominaciones_buscar(String nombre) throws SQLException{
//		Obj_Denominaciones denominaciones = new Obj_Denominaciones();
//		String query = "select folio from tb_denominaciones where nombre='"+nombre+"'";
//		Statement stmt = null;
//		try {
//			stmt = con.conexion().createStatement();
//			ResultSet rs = stmt.executeQuery(query);
//			while(rs.next()){
//				denominaciones.setFolio(rs.getInt("folio"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		finally{
//			if(stmt!=null){stmt.close();}
//		}
//		return denominaciones;
//	}
	
	public Obj_Turno Turn_buscar(String nombre) throws SQLException{
		Obj_Turno turno = new Obj_Turno();
		String query = "select folio from tb_turno where nombre='"+nombre+"'";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				turno.setFolio(rs.getInt("folio"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return turno;
	}
	
	public Obj_Establecimiento Establ_buscar_folio(int folio) throws SQLException{
		Obj_Establecimiento estab = new Obj_Establecimiento();
		String query = "select nombre from tb_establecimiento where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				estab.setNombre(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return estab;
	}
	
	public Obj_Puesto Pues_buscar(int folio) throws SQLException{
		Obj_Puesto puest = new Obj_Puesto();
		String query = "select nombre from tb_puesto where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				puest.setPuesto(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return puest;
	}
	
	public Obj_Tipo_Banco buscar_Banck(int folio) throws SQLException{
		Obj_Tipo_Banco banck = new Obj_Tipo_Banco();
		String query = "select nombre from tb_tipo_banco where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				banck.setBanco(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return banck;
	}
	
	public Obj_Divisa_Y_TipoDeCambio Divisas_buscar(int folio) throws SQLException{
		Obj_Divisa_Y_TipoDeCambio denominaciones = new Obj_Divisa_Y_TipoDeCambio();
		String query = "select nombre from tb_divisas_tipo_de_cambio where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				denominaciones.setNombre(rs.getString("nombre_divisas"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return denominaciones;
	}
	
	public Obj_Turno Turn_buscar(int folio) throws SQLException{
		Obj_Turno turno = new Obj_Turno();
		String query = "select nombre from tb_turno where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				turno.setNombre(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return turno;
	}
	
	
	public Obj_Revision_Lista_Raya Lista_buscar_folio(int folio) throws SQLException{
		Obj_Revision_Lista_Raya lista = new Obj_Revision_Lista_Raya();
		String query = "select folio from tb_pre_listaraya where folio_empleado = "+folio+" and status = 1;";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				lista.setFolio(rs.getInt("folio"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return lista;
	}
	
	
	public Obj_Configuracion_Sistema Configuracion_sistema() throws SQLException{
		Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema();
		String query ="select count(bono_10_12) as 'Couns' from tb_configuracion_sistema";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				configs.setCouns(rs.getInt("Couns"));
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return configs;
	}
	
	public Obj_Configuracion_Sistema Configuracion_sistema2() throws SQLException{
		Obj_Configuracion_Sistema configs = new Obj_Configuracion_Sistema();
		String query ="select * from tb_configuracion_sistema";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				configs.setBono_10_12(rs.getBoolean("bono_10_12"));
				configs.setBono_dia_extra(rs.getBoolean("bono_dia_extra"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return configs;
	}
	
	public Obj_Turno Horario_buscar(int folio) throws SQLException{
		Obj_Turno horario = new Obj_Turno();
		String query = "select horario from tb_turno where folio="+folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				horario.setHorario(rs.getString("horario"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return horario;
	}
	public Obj_Auto_Auditoria Autorizar_Audi() throws SQLException{
		Obj_Auto_Auditoria auditoria = new Obj_Auto_Auditoria();
		String query = "select autorizar_auditoria from tb_autorizaciones";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				auditoria.setAutorizar(rs.getBoolean(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return auditoria;
	}
	
	public Obj_Auto_Finanzas Autorizar_Finanzas() throws SQLException{
		Obj_Auto_Finanzas auditoria = new Obj_Auto_Finanzas();
		String query = "select autorizar_finanzas from tb_autorizaciones";
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
		    ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				auditoria.setAutorizar(rs.getBoolean(1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error");
			return null;
		}
		finally{
			 if (stmt != null) { stmt.close(); }
		}
		return auditoria;
	}
	
	public Obj_Bancos BancoExiste(int folio) throws SQLException{
		Obj_Bancos banck = new Obj_Bancos();
		String query = "select * from tb_bancos where status = 1 and folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				banck.setFolio_empleado(rs.getInt("folio_empleado"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return banck;
	}
	
	public Obj_Deduccion_Iasistencia DeduccionExiste(int folio) throws SQLException{
		Obj_Deduccion_Iasistencia Deduccion = new Obj_Deduccion_Iasistencia();
		String query = "select folio_empleado from tb_deduccion_inasistencia where status = 1 and folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				Deduccion.setFolio_empleado(rs.getInt("folio_empleado"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return Deduccion;
	}
	
	public Obj_Persecciones_Extra PerseccionExiste(int folio) throws SQLException{
		Obj_Persecciones_Extra perseccion = new Obj_Persecciones_Extra();
		String query = "select folio_empleado from tb_persecciones_extra where status = 1 and folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				perseccion.setFolio_empleado(rs.getInt("folio_empleado"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return perseccion;
	}
	
	
	public Obj_Revision_Lista_Raya Lista_Exist_Emp(int folio) throws SQLException{
		Obj_Revision_Lista_Raya lista = new Obj_Revision_Lista_Raya();
		String query = "select folio_empleado from tb_pre_listaraya where status=1 and folio_empleado ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				lista.setFolio_empleado(rs.getInt("folio_empleado"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return lista;
	}
	
	public String[][] getEstablecimientoLista(){
		int numero_lista = MaximoListaRaya()+1;
		int numero_nomina = MaximoListaNomina();
		
		String[][] Matriz = null;
		
		if(numero_lista == numero_nomina){
			String datosif = "select establecimiento, nomina from tb_captura_totales_nomina where lista_raya =" + numero_lista;
			Matriz = new String[getFilas(datosif)][2];
			Statement s;
			ResultSet rs;
			try {			
				s = con.conexion().createStatement();
				rs = s.executeQuery(datosif);
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1);
					Matriz[i][1] = rs.getString(2);
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}else{
			
			String datosif = "select nombre from tb_establecimiento where status = 1";
			Matriz = new String[getFilas(datosif)][2];
			Statement s;
			ResultSet rs;
			try {			
				s = con.conexion().createStatement();
				rs = s.executeQuery(datosif);
				int i=0;
				while(rs.next()){
					Matriz[i][0] = rs.getString(1);
					Matriz[i][1] = "";
					i++;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			
			
		}
		return Matriz;
	}
	
	public String[][] getUsuarioPermisos(){
		String datos = "exec lista_usuario_permisos";
		String[][] Matriz = new String[getFilas(datos)][3];
		Statement s;
		ResultSet rs;
		try {			
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			int i=0;
			while(rs.next()){
				Matriz[i][0] = rs.getString(1);
				Matriz[i][1] = rs.getString(2);
				Matriz[i][2] = rs.getString(3);
				i++;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public int getFilas(String qry){
		int filas=0;
		try {
			Statement s = con.conexion().createStatement();
			ResultSet rs = s.executeQuery(qry);
			while(rs.next()){
				filas++;
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return filas;
	}
	
	public String[][] getNomina(int Folio){
//		String datos = "exec sp_lista_nomina "+Folio;
		String datos = "exec sp_select_nomina "+Folio;
		String[][] Matriz = new String[getFilas(datos)+1][6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			int i=0;
			
			while(rs.next()){
				if(i==0){
					Matriz[i][0] = "Establecimiento";
					Matriz[i][1] = "Nomina";
					Matriz[i][2] = "Pago en Linea";
					Matriz[i][3] = "Total Cheque Nomina";
					Matriz[i][4] = "Lista de raya";
					Matriz[i][5] = "Diferencia";
					Matriz[i+1][0] = "   "+rs.getString(1);
					Matriz[i+1][1] = format.format(rs.getFloat(2))+"";
					Matriz[i+1][2] = format.format(rs.getFloat(3))+"";
					Matriz[i+1][3] = format.format(rs.getFloat(4))+"";
					Matriz[i+1][4] = format.format(rs.getFloat(5))+"";
					Matriz[i+1][5] = format.format(rs.getFloat(6))+"";
					i+=2;
				}else{
					Matriz[i][0] = "   "+rs.getString(1);
					Matriz[i][1] = format.format(rs.getFloat(2))+"";
					Matriz[i][2] = format.format(rs.getFloat(3))+"";
					Matriz[i][3] = format.format(rs.getFloat(4))+"";
					Matriz[i][4] = format.format(rs.getFloat(5))+"";
					Matriz[i][5] = format.format(rs.getFloat(6))+"";
					i++;
				}
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public int MaximoListaRaya(){
		String datos = "select	max(numero_lista) from tb_lista_raya";
		int valor = 0;
		Statement s;
		ResultSet rs;
		try {			
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				valor = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor; 
	}
	
	
	public int MaximoListaNomina(){
		String datos = "select max(lista_raya) from tb_captura_totales_nomina";
		int valor = 0;
		Statement s;
		ResultSet rs;
		try {			
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				valor = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return valor; 
	}
	public String[] getTotalesNomina(int Folio){
		
		String datos = "exec sp_total_nomina "+Folio;
		String[] Matriz = new String[6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				Matriz[0] = "   "+rs.getString(1);
				Matriz[1] = rs.getDouble(2)+"";
				Matriz[2] = format.format(rs.getFloat(3))+"";
				float totalChecke = rs.getFloat(4);
				float listaRaya = rs.getFloat(5);
				Matriz[3] = format.format(totalChecke)+"";
				Matriz[4] = format.format(listaRaya)+"";
				Matriz[5] = format.format(listaRaya-totalChecke)+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public String[] getTotalesCheque1(int Folio){
		String datos = "exec sp_total_cheque_1_super "+Folio;
		String[] Matriz = new String[6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				Matriz[0] = "   "+rs.getString(1);
				Matriz[1] = format.format(rs.getFloat(2))+"";
				Matriz[2] = format.format(rs.getFloat(3))+"";
				Matriz[3] = format.format(rs.getFloat(4))+"";
				Matriz[4] = "Cheque (2) super";
				Matriz[5] = format.format(rs.getFloat(6))+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public String[] getTotalesCheque1Ferre(int Folio){
		String datos = "exec sp_total_cheque_1_ferreteria "+Folio;
		String[] Matriz = new String[6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				Matriz[0] = "   "+rs.getString(1);
				Matriz[1] = format.format(rs.getFloat(2))+"";
				Matriz[2] = format.format(rs.getFloat(3))+"";
				Matriz[3] = format.format(rs.getFloat(4))+"";
				Matriz[4] = "Cheque (2) ferre refa";
				Matriz[5] = format.format(rs.getFloat(6))+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public String[] getTotalesCheque1Izacel(int Folio){
		String datos = "exec sp_total_cheque_1_izacel "+Folio;
		String[] Matriz = new String[6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			rs = s.executeQuery(datos);
			while(rs.next()){
				Matriz[0] = "   "+rs.getString(1);
				Matriz[1] = format.format(rs.getFloat(2))+"";
				Matriz[2] = format.format(rs.getFloat(3))+"";
				Matriz[3] = format.format(rs.getFloat(4))+"";
				Matriz[4] = "";
				Matriz[5] = "";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public String[] getNominaChequeABC(int Folio){
		String sp_total_cheques = "exec sp_total_cheques "+Folio;
		String[] Matriz = new String[6];
		Statement s;
		ResultSet rs;
		try {		
			DecimalFormat format = new DecimalFormat("#0.00");
			s = con.conexion().createStatement();
			
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				
				Matriz[0] = "   "+rs.getString(1);
				Matriz[1] = format.format(rs.getFloat(2))+"";
				Matriz[2] = "   "+rs.getString(3);
				Matriz[3] = format.format(rs.getFloat(4))+"";
				Matriz[4] = "   "+rs.getString(5);
				Matriz[5] = format.format(rs.getFloat(6))+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return Matriz; 
	}
	
	public float getNominaIndividual(String Establecimiento, int lista){
		String sp_total_cheques = "exec sp_return_nomina_value '"+Establecimiento+"',"+lista+";";
		float resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getFloat(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public float getBancosIndividual(String Establecimiento, int lista){
		String sp_total_cheques = "exec sp_return_bancos_value '"+Establecimiento+"',"+lista+";";
		float resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getFloat(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public float getListaRayaIndividual(String Establecimiento, int lista){
		String sp_total_cheques = "exec sp_return_lista_raya_value '"+Establecimiento+"',"+lista+";";
		float resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getFloat(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public float getDiferenciaIndividual(String Establecimiento, int lista){
		String sp_total_cheques = "exec sp_return_diferencia_value "+lista+",'"+Establecimiento+"';";
		float resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getFloat(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public int getMaximoNomina(){
		String sp_total_cheques = "exec sp_maximo_nomina";
		int resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getInt(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public int TemporadaNuevo(){
		String sp_total_cheques = "exec sp_temporada_nuevo";
		int resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getInt(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
	public Obj_Temporada TemporadaBuscar(int folio) throws SQLException{
		Obj_Temporada temporada = new Obj_Temporada();
		String query = "select * from tb_temporada where folio ="+ folio;
		Statement stmt = null;
		try {
			stmt = con.conexion().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				temporada.setFolio(rs.getInt("folio"));
				temporada.setDescripcion(rs.getString("descripcion"));
				temporada.setFecha_in(rs.getString("fecha_in"));
				temporada.setFecha_fin(rs.getString("fecha_fin"));
				temporada.setDia(rs.getString("dia"));	
				temporada.setStatus(rs.getBoolean("status") ? true : false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally{
			if(stmt!=null){stmt.close();}
		}
		return temporada;
	}

	public int Nueva_Actividad(){
		String sp_total_cheques = "exec sp_nueva_actividad";
		int resultado = 0;
		Statement s;
		ResultSet rs;
		try {		
			s = con.conexion().createStatement();
			rs = s.executeQuery(sp_total_cheques);
			
			while(rs.next()){
				resultado = rs.getInt(1);
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return resultado; 
	}
	
}
