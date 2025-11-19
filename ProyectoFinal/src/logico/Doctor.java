package logico;

import java.util.ArrayList;
import java.util.Date;

public class Doctor {
	
	private String idDoctor;
	private String nombre;
	private int cupoDiario;
	private ArrayList<Paciente> pacientes;
	private ArrayList<String> especialidades;

	
	public Doctor(String idDoctor, String nombre, int cupoDiario, ArrayList<String> especialidades) {
		
		super();
		this.idDoctor = idDoctor;
		this.nombre = nombre;
		this.cupoDiario = cupoDiario;
		this.especialidades = especialidades;
		pacientes = new ArrayList<>();
	}

	public String getIdDoctor() {
		return idDoctor;
	}

	public void setIdDoctor(String idDoctor) {
		this.idDoctor = idDoctor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCupoDiario() {
		return cupoDiario;
	}

	public void setCupoDiario(int cupoDiario) {
		this.cupoDiario = cupoDiario;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

	
	
}
