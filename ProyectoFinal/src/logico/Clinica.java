package logico;

import java.util.ArrayList;

public class Clinica {

    public static int genCodigoCitas = 1;
    public static int genCodigoConsultas = 1;

    private ArrayList<Cita> citas;
    private ArrayList<Consulta> consultas;
    //FALTAN VARIOS ATRIBUTOS, AÑADIR MIENTRAS LOS VAS REALIZANDO
    
    
    private static Clinica instancia = null;
    
    private Clinica() {
    	citas = new ArrayList<Cita>();
    	consultas = new ArrayList<Consulta>();
    	
    }
    
    public static Clinica getInstancia() {
        if(instancia == null) {
            instancia = new Clinica();
        }
        return instancia;
    }
    

}