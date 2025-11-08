package logico;

import java.util.Date;

public class Cita {
    private String id;
    private Paciente paciente;
    private Doctor doctor;
    private Date fechaHora;
    private String motivo;
    private EstadoCita estado;
    private Consulta consultaGenerada;
    
    public Cita(String id, Paciente paciente, Doctor doctor, Date fechaHora, String motivo) {
        this.id = id;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = EstadoCita.PROGRAMADA;
        this.consultaGenerada = null;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Date getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public EstadoCita getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
    
    public Consulta getConsultaGenerada() {
        return consultaGenerada;
    }
    
    public void setConsultaGenerada(Consulta consultaGenerada) {
        this.consultaGenerada = consultaGenerada;
    }
    
    public void cancelar() {
        if(estado == EstadoCita.PROGRAMADA) {
            estado = EstadoCita.CANCELADA;
        }
    }
    
    public void marcarNoAsistio() {
        if(estado == EstadoCita.PROGRAMADA) {
            estado = EstadoCita.NO_ASISTIO;
        }
    }
    
    public void completar() {
        if(consultaGenerada != null) {
            estado = EstadoCita.COMPLETADA;
        }
    }
    
}