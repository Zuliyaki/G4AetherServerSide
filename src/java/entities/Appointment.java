package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "appointment", schema = "aether")
@DiscriminatorValue("appointment")
@XmlRootElement
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAppointment;

    @NotNull
    private Boolean appointmentChange;

    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Psychologist psychologist;

    /**
     * Empty constructor
     */
    public Appointment() {
        super();
    }

    /**
     * Costructor with parameters
     *
     * @param idAppointmet
     * @param appointmentDate
     * @param appointmentChange
     * @param patient
     * @param psychologist
     */
    public Appointment(Long idAppointmet, Date appointmentDate, Boolean appointmentChange, Patient patient, Psychologist psychologist) {
        this.idAppointment = idAppointment;
        this.appointmentDate = appointmentDate;
        this.appointmentChange = appointmentChange;
        this.patient = patient;
        this.psychologist = psychologist;
    }

    //Getters & Setters
    
    /**
     * 
     * @return idAppointment
     */
    public Long getidAppointment() {
        return idAppointment;
    }

    /**
     * 
     * @param idMentalDisease 
     */
    public void setidAppointment(Long idMentalDisease) {
        this.idAppointment = idAppointment;
    }

    /**
     * 
     * @param appointmentDate 
     */
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    /**
     * 
     * @return appointmentDate
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * 
     * @param appointmentChange 
     */
    public void setAppointmentChange(Boolean appointmentChange) {
        this.appointmentChange = appointmentChange;
    }

    /**
     * 
     * @return appointmentChange
     */
    public Boolean getAppointmentChange() {
        return appointmentChange;
    }

    /**
     * 
     * @param patient 
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * 
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * 
     * @param psychologist 
     */
    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    /**
     * 
     * @return psychologist
     */
    public Psychologist getPsychologist() {
        return psychologist;
    }

}
