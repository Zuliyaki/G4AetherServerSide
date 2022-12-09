package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "appointments", schema = "aether")
public class Appointment implements Serializable {
    
    @Id 
    @Temporal(TemporalType.TIMESTAMP)
    private Boolean appointmentChange;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date appointmentDate;
    
    @ManyToOne
    private Patient patient;
    
    @OneToMany(mappedBy="patient")
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
