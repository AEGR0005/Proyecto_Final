package logico;

import java.util.ArrayList;

public class Clinica {

    public static int genCodigoCitas = 1;
    public static int genCodigoConsultas = 1;

    private ArrayList<Cita> citas;
    private ArrayList<Paciente> pacientes;
    private ArrayList<Doctor> doctores;
    //FALTAN VARIOS ATRIBUTOS, AÑADIR MIENTRAS LOS VAS REALIZANDO
    
    
    private static Clinica instancia = null;
    
    private Clinica() {
    	citas = new ArrayList<Cita>();
    	pacientes = new ArrayList<Paciente>();
    	doctores = new ArrayList<Doctor>();
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
}