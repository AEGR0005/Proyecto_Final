package logico;


import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;


public class Clinica implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2147265011502063886L;

	public static Usuario loginUsuario;
	public static int genCodigoPacientes = 1;
	public static int genCodigoCitas = 1;
	public static int genCodigoConsultas = 1;
	public static int genCodigoDiagnosticos = 1;
	public static int genCodigoDoctores = 1;
	public static int genCodigoVacuna = 1;
	public static int genCodigoEnfermedad = 1;

	private ArrayList<Usuario> usuarios;
	private ArrayList<Cita> citas;
	private ArrayList<Paciente> pacientes;
	private ArrayList<Doctor> doctores;
	private ArrayList<Consulta> consultas;
	private ArrayList<Enfermedad> enfermedades;
	private ArrayList<Vacuna> vacunas;
	//private int[6] contadores;
	private ArrayList<Integer> contadores;


	private static Clinica instancia = null;

	private Clinica() {
		citas = new ArrayList<Cita>();
		pacientes = new ArrayList<Paciente>();
		doctores = new ArrayList<Doctor>();
		consultas = new ArrayList<Consulta>();
		enfermedades = new ArrayList<Enfermedad>();
		vacunas = new ArrayList<Vacuna>();
		usuarios = new ArrayList<Usuario>();
		iniciarContadores();
	}

	public static Clinica getInstancia() {
		if(instancia == null) {
			instancia = new Clinica();
		}
		return instancia;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public ArrayList<Paciente> getPacientes() {
		return pacientes;
	}


	public ArrayList<Doctor> getDoctores() {
		return doctores;
	}


	public ArrayList<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(ArrayList<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public boolean confirmLogin(String nombre, String password) {
		boolean login = false;
		int i = 0;

		while(!login && i < usuarios.size()) {
			Usuario usuario = usuarios.get(i);

			if(usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password)) {
				loginUsuario = usuario;
				login = true;
			}
		}


		return login;
	}

	public void regUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public void setClinica(Clinica auxClinica) {
		if(auxClinica != null)
			instancia = auxClinica;
	}

	public void initInfo() {
		
			crearDoctorPrueba();
			Doctor aux = Clinica.getInstancia().getDoctores().get(0);
			Clinica.getInstancia().crearPacientePrueba("Cristina Garcia Hernandez", "0315400566");
			Clinica.getInstancia().crearPacientePrueba("Cristina Mirabal", "0319084701");
			Clinica.getInstancia().crearPacientePrueba("Altagracia Rodríguez Fernandez", "0315400566");
			Clinica.getInstancia().crearPacientePrueba("Maria Antonieta De las Nieves", "0312435679");
			aux.setPacientes(Clinica.getInstancia().getPacientes());
			
	}
	
	private void iniciarContadores() {
		contadores = new ArrayList<Integer>();
		
		contadores.add(genCodigoPacientes);
		contadores.add(genCodigoDoctores);
		contadores.add(genCodigoCitas);
		contadores.add(genCodigoConsultas);
		contadores.add(genCodigoDiagnosticos);
		contadores.add(genCodigoEnfermedad);
		contadores.add(genCodigoVacuna);
	}
	
	public void asignarContadores() {
		genCodigoPacientes = contadores.get(0);
		genCodigoDoctores = contadores.get(1);
		genCodigoCitas = contadores.get(2);
		genCodigoConsultas = contadores.get(3);
		genCodigoDiagnosticos = contadores.get(4);
		genCodigoEnfermedad = contadores.get(5);
		genCodigoVacuna = contadores.get(6);
	}
	
	public void guardarContadores() {
		contadores.set(0, genCodigoPacientes);
		contadores.set(1, genCodigoDoctores);
		contadores.set(2, genCodigoCitas);
		contadores.set(3, genCodigoConsultas);
		contadores.set(4, genCodigoDiagnosticos);
		contadores.set(5, genCodigoEnfermedad);
		contadores.set(6, genCodigoVacuna);
	}
	
	/*
	public void guardarContadores() {

		contadores[0] = genCodigoPacientes;
		contadores[1] = genCodigoDoctores;
		contadores[2] = genCodigoCitas;
		contadores[3] = genCodigoConsultas;
		contadores[4] = genCodigoDiagnosticos;
		contadores[5] = genCodigoEnfermedad;		
		contadores[6] =	genCodigoVacuna;			
	}*/
	
	/*
	public void iniciarContadores() {

		genCodigoPacientes = contadores[0];
		genCodigoDoctores = contadores[1];
		genCodigoCitas = contadores[2];
		genCodigoConsultas = contadores[3];
		genCodigoDiagnosticos = contadores[4];
		genCodigoEnfermedad = contadores[5];		
		genCodigoVacuna = contadores[6];			
	}*/

	public Paciente buscarPacienteXId(String id) {
		Paciente auxPaciente = null;
		int i = 0;

		while(auxPaciente == null && i < pacientes.size()) {
			if(pacientes.get(i).getIdPaciente().equals(id))
				auxPaciente = pacientes.get(i);
			i++;
		}


		return auxPaciente;
	}

	public Cita buscarCitaXId(String id) {
		Cita auxCita = null;
		int i = 0;


		while(auxCita == null && i < citas.size()) {

			if(citas.get(i).getId().equals(id)) 
				auxCita = citas.get(i);

		}


		return auxCita;
	}

	public Doctor buscarDoctorXId(String id) {
		Doctor auxDoctor = null;
		int i = 0;

		while(auxDoctor == null && i < doctores.size()) {

			if(doctores.get(i).getIdDoctor().equals(id))
				auxDoctor = doctores.get(i);
			i++;

		}

		return auxDoctor;
	}

	public void regPaciente(Paciente paciente) {
		//crearDoctorPrueba();
		pacientes.add(paciente);
		genCodigoPacientes++;
	}

	private void regCita(Cita cita) {
		citas.add(cita);
	}


	public Cita crearCita(Paciente paciente, Doctor doctor, Date fecha, String motivo) {

		String idCita = "C-" + genCodigoCitas;
		Cita nuevaCita = new Cita(idCita, paciente, doctor, fecha, motivo);
		regCita(nuevaCita);
		genCodigoCitas++;
		return nuevaCita; 
	}

	public Consulta realizarConsulta(Cita cita) {
		if(cita == null || cita.getEstado() != EstadoCita.PROGRAMADA) {
			return null; 
		}

		String idConsulta = "CONS-" + genCodigoConsultas;
		Consulta nuevaConsulta = new Consulta(idConsulta, cita.getPaciente(), cita.getDoctor(), cita.getFechaHora());
		consultas.add(nuevaConsulta);
		cita.getPaciente().getHistorialClinico().add(nuevaConsulta);
		cita.getPaciente().addConsultaToResumen(nuevaConsulta);
		cita.setConsultaGenerada(nuevaConsulta);
		cita.completar();
		genCodigoConsultas++;

		return nuevaConsulta;
	}


	private int contarCitasXDia(Doctor doctor, Date fecha) {
		Calendar calendFecha = Calendar.getInstance();
		calendFecha.setTime(fecha);

		int contador = 0;

		for(int i = 0; i < citas.size(); i++) {
			Cita cita = citas.get(i);

			if(cita.getDoctor().getIdDoctor().equals(doctor.getIdDoctor()) && 
					cita.getEstado() == EstadoCita.PROGRAMADA) {
				Calendar calendCita = Calendar.getInstance();
				calendCita.setTime(cita.getFechaHora());

				if(calendFecha.get(Calendar.YEAR) == calendCita.get(Calendar.YEAR) &&
						calendFecha.get(Calendar.DAY_OF_YEAR) == calendCita.get(Calendar.DAY_OF_YEAR)) {
					contador++;
				}
			}
		}
		return contador;
	}


	public ArrayList<Consulta> getConsultasXDoctor(Doctor doctor) {
		ArrayList<Consulta> consultasDoctor = new ArrayList<>();

		for(Consulta consulta : consultas) {
			if(consulta.getDoctor().getIdDoctor().equals(doctor.getIdDoctor())) {
				consultasDoctor.add(consulta);
			}
		}

		return consultasDoctor;
	}


	public void crearDoctorPrueba() {
		ArrayList<String> especialidades = new ArrayList<>();
		especialidades.add("Pediatría");
		especialidades.add("Dermatología");

		Doctor doctorPrueba = new Doctor(
				"DOC"+ genCodigoDoctores,
				"El tejas",
				20,
				especialidades
				);

		doctores.add(doctorPrueba);
	}

	public Paciente crearPacientePrueba(String nombre, String cedula) {

		Paciente auxPaciente = new Paciente("PAC-"+genCodigoPacientes, nombre, cedula, "849", null, "U", (float)100, (float)5.2, "A+", null);
		genCodigoPacientes++;
		pacientes.add(auxPaciente);

		return auxPaciente;
	}

	public Consulta buscarConsultaXId(String id) {
		Consulta auxConsulta = null;
		int i = 0;

		while(auxConsulta == null && i < consultas.size()) {
			if(consultas.get(i).getId().equals(id)) {
				auxConsulta = consultas.get(i);
			}
			i++;
		}

		return auxConsulta;
	}


	public ArrayList<Consulta> getConsultasVisiblesXDoctor(Doctor doctor) {
		ArrayList<Consulta> consultasVisibles = new ArrayList<>();

		for(Consulta consulta : consultas) {
			if(consulta.getDoctor().getIdDoctor().equals(doctor.getIdDoctor())) {
				consultasVisibles.add(consulta);
			}
		}


		for(Paciente paciente : pacientes) {
			for(Consulta consultaImportante : paciente.getResumen()) {
				boolean yaExiste = false;
				int i = 0;
				while(i < consultasVisibles.size() && !yaExiste) {
					if(consultasVisibles.get(i).getId().equals(consultaImportante.getId())) {
						yaExiste = true;
					}
					i++;
				}

				if(!yaExiste) {
					consultasVisibles.add(consultaImportante);
				}
			}
		}

		return consultasVisibles;
	}

	public void registrarEnfermedad(Enfermedad enfermedad) {
		enfermedades.add(enfermedad);
		genCodigoEnfermedad++;
	}

	public void registrarEnfermedadBajoVigilancia(Enfermedad enfermedad) {
		enfermedad.activarVigilancia();
		enfermedades.add(enfermedad);
		genCodigoEnfermedad++;
	}

	public Enfermedad buscarEnfermedadXId(String id) {
		Enfermedad aux = null;
		int i = 0;

		while(aux == null && i < enfermedades.size()) {
			if(enfermedades.get(i).getId().equals(id)) {
				aux = enfermedades.get(i);
			}
			i++;
		}

		return aux;
	}

	public void reportarCasoEnfermedad(String id) {
		Enfermedad enf = buscarEnfermedadXId(id);
		if(enf != null) {
			enf.reportarCaso();
		}
	}


	public String getDateString(Date fecha) {
		Calendar calen = Calendar.getInstance();
		calen.setTime(fecha);

		return calen.get(Calendar.DAY_OF_MONTH)+"/"+(calen.get(Calendar.MONTH)+1)+"/"+calen.get(Calendar.YEAR);
	}



	public ArrayList<Enfermedad> getEnfermedades() {
		return enfermedades;
	}
	
	public static Usuario getUsuarioActual() {
	    return loginUsuario;
	}

}