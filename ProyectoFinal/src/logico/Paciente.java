package logico;

import java.util.ArrayList;

public class Paciente {

	private String idPaciente;
	private String nombre;
	private String cedula;
	private String telefono;
	private String fecNacim;

	private ArrayList<Vacuna> vacunas;
	private ArrayList<Consulta> resumen; 
	// Son las consultas que cada doctor elige como importantes. Todos los doctores las pueden ver.
	private ArrayList<Consulta> historialClinico; 
	// Guarda todas las consultas que el paciente a tenido. Al mostrar las de cada doctor, se puede hacer revisando a qué doctor pertenece cada consulta.

	public Paciente(String idPaciente, String nombre, String cedula, String telefono, String fecNacim) {
		super();
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.fecNacim = fecNacim;

		vacunas = new ArrayList<>();
		resumen = new ArrayList<>();
		historialClinico = new ArrayList<>();
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

	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public ArrayList<Consulta> getResumen() {
		return resumen;
	}

	public void setResumen(ArrayList<Consulta> resumen) {
		this.resumen = resumen;
	}

	public ArrayList<Consulta> getHistorialClinico() {
		return historialClinico;
	}

	public void setHistorialClinico(ArrayList<Consulta> historialClinico) {
		this.historialClinico = historialClinico;
	}
	
	public void addConsultaToResumen(Consulta consulta) {
		if(consulta.getEsImportante())
			resumen.add(consulta);
	}
	
	public ArrayList<String> getDoctores(){
		ArrayList<String> doctores = new ArrayList<>();
		
		for (Consulta consulta : historialClinico) {
			String nomDoc = consulta.getDoctor().getNombre();
			
			if(!doctores.contains(nomDoc))
				doctores.add(nomDoc);
		}
		
		if(doctores.size() == 0)
			doctores = null;
		
		return doctores;
	}
	
	/*
	public void mostrarHistorialXDoctor(Doctor doctor) {
		
		
		
	}*/

}