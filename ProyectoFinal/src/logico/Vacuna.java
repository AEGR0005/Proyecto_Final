package logico;

import java.util.ArrayList;
import java.util.Date;

public class Vacuna {
	
	//General
	private String id;
	protected String nombre;
	protected Enfermedad enfermedad;
	protected int dosis;
	protected boolean bajoControl;
	protected int edadMinima;
	
	//Paciente
	//private String id;
	private int dosisAplicadas;
	private ArrayList<Date> fecAplicacion;
	private boolean aplicada;
	
}
