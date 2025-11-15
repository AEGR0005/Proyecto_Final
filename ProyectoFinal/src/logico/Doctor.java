package logico;

import java.util.ArrayList;
import java.util.Date;

public class Doctor {
	
	private String idDoctor;
	private String nombre;
	private int cupoDiario;
	private ArrayList<Paciente> pacientes;
	private ArrayList<String> especialidades;
	private Date inicioTanda;
	private Date finTanda;
	private int duracionCita;
	private ArrayList<HorarioDisponible> horariosDisponibles;
	// IMPORTANTE UTILIZAR ESTE ATRIBUTO!!!!!!!!
	
	public Doctor(String idDoctor, String nombre, int cupoDiario, ArrayList<String> especialidades, Date inicioTanda, Date finTanda, 
			int duracionCita) {
		
		super();
		this.idDoctor = idDoctor;
		this.nombre = nombre;
		this.cupoDiario = cupoDiario;
		this.especialidades = especialidades;
		this.inicioTanda = inicioTanda;
		this.finTanda = finTanda;
		this.duracionCita = duracionCita;
		pacientes = new ArrayList<>();
		horariosDisponibles = new ArrayList<>();
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

	public Date getInicioTanda() {
		return inicioTanda;
	}

	public void setInicioTanda(Date inicioTanda) {
		this.inicioTanda = inicioTanda;
	}

	public Date getFinTanda() {
		return finTanda;
	}

	public void setFinTanda(Date finTanda) {
		this.finTanda = finTanda;
	}

	public int getDuracionCita() {
		return duracionCita;
	}

	public void setDuracionCita(int duracionCita) {
		this.duracionCita = duracionCita;
	}

	public ArrayList<HorarioDisponible> getHorariosDisponibles() {
		return horariosDisponibles;
	}

	public void setHorariosDisponibles(ArrayList<HorarioDisponible> horariosDisponibles) {
		this.horariosDisponibles = horariosDisponibles;
	}


	
	
	
	
	
}
