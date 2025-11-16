package logico;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;


public class Clinica {

    public static int genCodigoCitas = 1;
    public static int genCodigoConsultas = 1;
    public static int genCodigoDiagnosticos = 1;

    private ArrayList<Cita> citas;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Doctor> doctores;
    private ArrayList<Consulta> consultas;
    //FALTAN VARIOS ATRIBUTOS, AÑADIR MIENTRAS LOS VAS REALIZANDO
    
    
    private static Clinica instancia = null;
    
    private Clinica() {
    	citas = new ArrayList<Cita>();
    	pacientes = new ArrayList<Paciente>();
    	doctores = new ArrayList<Doctor>();
    	consultas = new ArrayList<Consulta>();
    }
    
    public static Clinica getInstancia() {
        if(instancia == null) {
            instancia = new Clinica();
        }
        return instancia;
    }
    
    public Paciente buscarPacienteXId(String id) {
    	Paciente auxPaciente = null;
    	int i = 0;
    	
    	
    	while(auxPaciente == null && i < pacientes.size()) {
    		
    		if(pacientes.get(i).getIdPaciente().equals(id)) 
    			auxPaciente = pacientes.get(i);
    		
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
    
    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }

    public ArrayList<Doctor> getDoctores() {
        return doctores;
    }
    
    public Doctor buscarDoctorXId(String id) {
    	Doctor auxDoctor = null;
    	int i = 0;
    	
    	while(auxDoctor == null && i < doctores.size()) {
    		
    		//if(doctores.get(i).get)
    		
    	}
    	
    	return auxDoctor;
    }
    
    private void regCita(Cita cita) {
    	citas.add(cita);
    }
    
    public Cita crearCita(Paciente paciente, Doctor doctor, Date fechaHora, String sintomas) {
        
        String idCita = "C-" + genCodigoCitas;
        Cita nuevaCita = new Cita(idCita, paciente, doctor, fechaHora, sintomas);
        regCita(nuevaCita);
        genCodigoCitas++;
        return nuevaCita; 
    }
    
    public Consulta realizarConsulta(Cita cita) {
    	
        if(cita == null || cita.getEstado() != EstadoCita.PROGRAMADA) {
            return null; 
        }
        
        String idConsulta = "CONS-" + genCodigoConsultas;
        Consulta nuevaConsulta = new Consulta(idConsulta,cita);
        consultas.add(nuevaConsulta);
        cita.getPaciente().getHistorialClinico().add(nuevaConsulta);
        cita.setConsultaGenerada(nuevaConsulta);
        cita.completar();
        genCodigoConsultas++;
        genCodigoDiagnosticos++;
        
        return nuevaConsulta;
    }
    
    private HorarioDisponible buscarHorarioXDia(Doctor doctor, int diaCalendar) {
        HorarioDisponible horarioEncontrado = null;
        int i = 0;
        
        while(horarioEncontrado == null && i < doctor.getHorariosDisponibles().size()) {
            HorarioDisponible horario = doctor.getHorariosDisponibles().get(i);
            
            if(horario.getDiaSemana() == diaCalendar) {
                horarioEncontrado = horario;
            }
            i++;
        }
        
        return horarioEncontrado;
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

}