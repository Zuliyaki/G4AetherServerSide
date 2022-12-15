package entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
;

@Entity
@Table(name = "psychologist", schema = "aether")
@DiscriminatorValue("psychologist")
public class Psychologist extends User{
    
    @NotNull
    private String titulation;
    
    @NotNull
    private String invitationCode;
    
    @OneToMany(mappedBy = "psychologist")
    private Set<Patient> patient;
    
    @OneToMany(mappedBy = "psychologist")
    private Set<Appointment> appointments;
    
    /**
     * Empty constructor
     */
    public Psychologist() {
        super();
    }
    //OnlyPyschologist constructor
    public Psychologist(String titulation, String invitationCode, Set<Patient> patient, Set<Appointment> appointments) {
        this.titulation = titulation;
        this.invitationCode = invitationCode;
        this.patient = patient;
        this.appointments = appointments;
    }
    //Pshychologist with super constructor
    public Psychologist(String titulation, String invitationCode, Set<Patient> patient, Set<Appointment> appointments, String dni, String fullName, Date birthDate, String password, Integer phoneNumber, String email) {
        super(dni, fullName, birthDate, password, phoneNumber, email);
        this.titulation = titulation;
        this.invitationCode = invitationCode;
        this.patient = patient;
        this.appointments = appointments;
    }
    

    //Getters & Setters

    public String getTitulation() {
        return titulation;
    }

    public void setTitulation(String titulation) {
        this.titulation = titulation;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Set<Patient> getPatient() {
        return patient;
    }

    public void setPatient(Set<Patient> patient) {
        this.patient = patient;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    

   
    
}
