package entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name= "diagnosis", schema= "aether" )
public class Diagnosis implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private String id;
    private Date diagnosisDate;
    @Temporal(TemporalType.DATE)
    private Date lastDiagnosisChangeDate;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Psychologist psychologist;
    @ManyToOne
    private MentalDisease mentalDisease;
    @OneToMany(mappedBy = "diagnosis")
    private Set<Treatment> treatments;
    @NotNull
    private Boolean onTherapy;
    
    /**
     * Empty constructor
     */
    public Diagnosis() {
        super();
    }

    /**
     *Costructor with parameters
     *
     * @param id
     * @param diagnosisDate
     * @param lastDiagnosisChangeDate
     * @param patient
     * @param psychologist
     * @param mentalDisease
     * @param onTherapy
     */
    public Diagnosis(String id, Date diagnosisDate, Date lastDiagnosisChangeDate, Patient patient,
                     Psychologist psychologist, MentalDisease mentalDisease, Boolean onTherapy) {
        this.id = id;
        this.diagnosisDate = diagnosisDate;
        this.lastDiagnosisChangeDate = lastDiagnosisChangeDate;
        this.patient = patient;
        this.psychologist = psychologist;
        this.mentalDisease = mentalDisease;
        this.onTherapy = onTherapy;
    }

    //Getters & Setters
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setLastDiagnosisChangeDate(Date lastDiagnosisChangeDate) {
        this.lastDiagnosisChangeDate = lastDiagnosisChangeDate;
    }

    public Date getLastDiagnosisChangeDate() {
        return lastDiagnosisChangeDate;
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

    public void setMentalDisease(MentalDisease mentalDisease) {
        this.mentalDisease = mentalDisease;
    }

    public MentalDisease getMentalDisease() {
        return mentalDisease;
    }

    public void setOnTherapy(Boolean onTherapy) {
        this.onTherapy = onTherapy;
    }

    public Boolean getOnTherapy() {
        return onTherapy;
    }
    //HASCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.diagnosisDate);
        hash = 71 * hash + Objects.hashCode(this.lastDiagnosisChangeDate);
        hash = 71 * hash + Objects.hashCode(this.patient);
        hash = 71 * hash + Objects.hashCode(this.psychologist);
        hash = 71 * hash + Objects.hashCode(this.mentalDisease);
        hash = 71 * hash + Objects.hashCode(this.treatments);
        hash = 71 * hash + Objects.hashCode(this.onTherapy);
        return hash;
    }
    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diagnosis other = (Diagnosis) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.mentalDisease, other.mentalDisease)) {
            return false;
        }
        return true;
    }
    //TO STRING
    @Override
    public String toString() {
        return "Diagnosis{" + "id=" + id + ", diagnosisDate=" + diagnosisDate + ", lastDiagnosisChangeDate=" + lastDiagnosisChangeDate + ", patient=" + patient + ", psychologist=" + psychologist + ", mentalDisease=" + mentalDisease + ", treatments=" + treatments + ", onTherapy=" + onTherapy + '}';
    }
    
    
}
