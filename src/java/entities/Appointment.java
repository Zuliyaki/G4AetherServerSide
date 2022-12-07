package packages;

import java.util.Date;

public class Appointment {
    private Date appointmentDate;
    private Boolean appointmentChange;
    private Patient patient;
    private Psychologist psychologist;

    /**
     * Empty constructor
     */
    public Appointment() {
        super();
    }

    /**
     *Costructor with parameters
     *
     * @param appointmentDate
     * @param appointmentChange
     * @param patient
     * @param psychologist
     */
    public Appointment(Date appointmentDate, Boolean appointmentChange, Patient patient, Psychologist psychologist) {
        this.appointmentDate = appointmentDate;
        this.appointmentChange = appointmentChange;
        this.patient = patient;
        this.psychologist = psychologist;
    }

    //Getters & Setters
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentChange(Boolean appointmentChange) {
        this.appointmentChange = appointmentChange;
    }

    public Boolean getAppointmentChange() {
        return appointmentChange;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

}
