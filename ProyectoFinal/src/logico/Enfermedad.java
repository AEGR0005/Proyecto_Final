package logico;

public class Enfermedad {
	private String id;
	private String nombre;
	private boolean vigilancia;
	
	public Enfermedad(String id, String nombre, boolean vigilancia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.vigilancia = vigilancia;
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
	public boolean isVigilancia() {
		return vigilancia;
	}
	public void setVigilancia(boolean vigilancia) {
		this.vigilancia = vigilancia;
	}
	
}
