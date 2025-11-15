package logico;

import java.util.Calendar;

public class HorarioDisponible {
    private String id;
    private Doctor doctor;
    private int diaSemana; 
    private String horaInicio;
    private String horaFin;
    private int duracionCita;
    
    public HorarioDisponible(String id, Doctor doctor, int diaSemana,
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
    
    public int getDiaSemana() {
        return diaSemana;
    }
    
    public void setDiaSemana(int diaSemana) {
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