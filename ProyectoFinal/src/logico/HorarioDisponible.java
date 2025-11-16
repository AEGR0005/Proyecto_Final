package logico;

public class HorarioDisponible {
    private String id;
    private Doctor doctor;
    private DiaSemana diaSemana;
    private String horaInicio;
    private String horaFin;
    private int duracionCita;
    
    // ESTA CLASE SE UTILIZARÁ PARA SABER CUALES CITAS SON DISPONIBLES PARA EL DOCTOR
    
    public HorarioDisponible(String id, Doctor doctor, DiaSemana diaSemana,
                            String horaInicio, String horaFin, int duracionCita) {
        this.id = id;
        this.doctor = doctor;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.duracionCita = duracionCita;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public int getDuracionCita() {
		return duracionCita;
	}

	public void setDuracionCita(int duracionCita) {
		this.duracionCita = duracionCita;
	}
    
    
}