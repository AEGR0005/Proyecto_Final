package logico;

import java.sql.Date;
import java.util.ArrayList;

public class Paciente {

	private String idPaciente;
	private String nombre;
	private String cedula;
	private String telefono;
	private Date fecNacim;
	private float peso;
	private float estatura;
	private String tipoSangre;

	private ArrayList<Vacuna> vacunas;
	private ArrayList<Consulta> resumen; 
	// Son las consultas que cada doctor elige como importantes. Todos los doctores las pueden ver.
	private ArrayList<Consulta> historialClinico; 
	// Guarda todas las consultas que el paciente a tenido. Al mostrar las de cada doctor, se puede hacer revisando a qué doctor pertenece cada consulta.
    private ArrayList<Enfermedad> enfermedades;
    

	public Paciente(String idPaciente, String nombre, String cedula, String telefono, Date fecNacim) {
		super();
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.fecNacim = fecNacim;

		vacunas = new ArrayList<>();
		resumen = new ArrayList<>();
		historialClinico = new ArrayList<>();
		enfermedades = new ArrayList<>();
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

	public Date getFecNacim() {
		return fecNacim;
	}

	public void setFecNacim(Date fecNacim) {
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

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
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

	public void mostrarHistorialXDoctor(Doctor doctor) {
		
		
		
	}

	public ArrayList<Enfermedad> getEnfermedades() {
	    return enfermedades;
	}

	public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
	    this.enfermedades = enfermedades;
	}
	
	public void agregarEnfermedad(Enfermedad e) {
        if(!enfermedades.contains(e)) {
            enfermedades.add(e);
        }
    }

	
    public Enfermedad buscarEnfermedadPorId(String id) {
        for (Enfermedad e : enfermedades) {
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }
    

    public ArrayList<Enfermedad> getEnfermedadesBajoVigilancia() {
        ArrayList<Enfermedad> lista = new ArrayList<>();
        for (Enfermedad e : enfermedades) {
            if (e.isVigilancia()) {
                lista.add(e);
            }
        }
        return lista;
    }
    
}
