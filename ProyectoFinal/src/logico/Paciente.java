package logico;

import java.util.ArrayList;

public class Paciente {
	
	private String idPaciente;
	private String nombre;
	private String cedula;
	private String telefono;
	private String fecNacim;
	
	
	public Paciente(String idPaciente, String nombre, String cedula, String telefono, String fecNacim) {
		super();
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.fecNacim = fecNacim;
	}
	public String getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFecNacim() {
		return fecNacim;
	}
	public void setFecNacim(String fecNacim) {
		this.fecNacim = fecNacim;
	}
	
		
}