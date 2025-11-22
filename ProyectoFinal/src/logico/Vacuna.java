package logico;

import java.util.ArrayList;
import java.util.Date;

public class Vacuna {
	
	private String id;
	private String nombre;
	private Enfermedad enfermedad;
	private int edadMinima;
	
	public Vacuna(String id, String nombre, Enfermedad enfermedad, int edadMinima) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.edadMinima = edadMinima;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
	public int getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	
	
	
}
