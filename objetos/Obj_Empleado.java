package objetos;

import java.sql.SQLException;

import SQL.ActualizarSQL;
import SQL.BuscarSQL;
import SQL.GuardarSQL;

public class Obj_Empleado {
	
	private int folio;
	private int no_checador;
	private String nombre;
	private String ap_paterno;
	private String ap_materno;
	private int establecimiento;
	private int puesto;
	private int turno;
	private int descanso;
	private int dobla;
	private float pension_alimenticia;
	private int sueldo;
	private int bono;
	private int prestamo;
	private float infonavit;
	private String targeta_nomina;
	private int tipo_banco;
	private boolean fuente_sodas;
	private boolean gafete;
	private int status;
	private String fecha;
	private String observasiones;
	private String foto;
	private String fecha_nacimiento;
	
	public Obj_Empleado(){
		folio=0; no_checador=0; nombre=""; ap_paterno=""; ap_materno=""; establecimiento=0; prestamo=0; foto ="";
		puesto=0; turno=0; descanso=0; dobla=0; pension_alimenticia=0; sueldo=0; bono=0; status=0; fecha=""; fuente_sodas=false; 
		infonavit=0; targeta_nomina=""; tipo_banco=0; observasiones="";
	}

	public boolean getFuente_sodas() {
		return fuente_sodas;
	}

	public void setFuente_sodas(boolean fuenteSodas) {
		fuente_sodas = fuenteSodas;
	}

	public boolean getGafete() {
		return gafete;
	}

	public void setGafete(boolean gafete) {
		this.gafete = gafete;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public int getNo_checador() {
		return no_checador;
	}

	public void setNo_checador(int noChecador) {
		no_checador = noChecador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAp_paterno() {
		return ap_paterno;
	}

	public void setAp_paterno(String apPaterno) {
		ap_paterno = apPaterno;
	}

	public String getAp_materno() {
		return ap_materno;
	}

	public void setAp_materno(String apMaterno) {
		ap_materno = apMaterno;
	}

	public int getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(int establecimiento) {
		this.establecimiento = establecimiento;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getDescanso() {
		return descanso;
	}

	public void setDescanso(int descanso) {
		this.descanso = descanso;
	}

	public int getDobla() {
		return dobla;
	}

	public void setDobla(int dobla) {
		this.dobla = dobla;
	}

	public float getPension_alimenticia() {
		return pension_alimenticia;
	}

	public void setPension_alimenticia(float pensionAlimenticia) {
		pension_alimenticia = pensionAlimenticia;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public int getBono() {
		return bono;
	}

	public void setBono(int bono) {
		this.bono = bono;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(int prestamo) {
		this.prestamo = prestamo;
	}
	
	public float getInfonavit() {
		return infonavit;
	}

	public void setInfonavit(float infonavit) {
		this.infonavit = infonavit;
	}
	
	public String getTargeta_nomina() {
		return targeta_nomina;
	}

	public void setTargeta_nomina(String targeta_nomina) {
		this.targeta_nomina = targeta_nomina;
	}

	public int getTipo_banco() {
		return tipo_banco;
	}

	public void setTipo_banco(int tipo_banco) {
		this.tipo_banco = tipo_banco;
	}

	public String getObservasiones() {
		return observasiones;
	}

	public void setObservasiones(String observasiones) {
		this.observasiones = observasiones;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public boolean guardar(){ return new GuardarSQL().Guardar_Empleado(this); }
	
	public Obj_Empleado buscar(int folio){ 
		try {
			return new BuscarSQL().Empleado(folio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; 
	}
	
	public boolean actualizar(int folio){ return new ActualizarSQL().Empleado(this,folio); }
	
	public Obj_Empleado buscar_nuevo() throws SQLException{ return new BuscarSQL().Empleado_Nuevo(); }
	
}
