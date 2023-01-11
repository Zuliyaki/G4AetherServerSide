package entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "diagnosis", schema = "aether")
@NamedQueries({
    @NamedQuery(
            name = "findAllDiagnosis", query = "SELECT dia FROM Diagnosis dia"
    )
    ,
    @NamedQuery(
            name = "findDiagnosisById", query = "SELECT dia FROM Diagnosis dia WHERE dia.diagnosisId=:diagnosisId"
    )
    ,
    @NamedQuery(
            name = "findAllDiagnosisByPatient", query = "SELECT dia FROM Diagnosis dia WHERE dia.patient=:patient"
    )
    ,
    @NamedQuery(
            name = "findPatientDiagnosisByDate", query = "SELECT dia FROM Diagnosis dia WHERE dia.patient=:patient and dia.diagnosisDate=:diagnosisDate"
    )
    ,
    @NamedQuery(
            name = "findAllIfPatientOnTeraphy", query = "SELECT dia FROM Diagnosis dia WHERE dia.patient=:patient and dia.onTherapy=true"
    )
})
@XmlRootElement
public class Diagnosis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long diagnosisId;
    @Temporal(TemporalType.DATE)
    private Date diagnosisDate;
    @Temporal(TemporalType.DATE)
    private Date lastDiagnosisChangeDate;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Psychologist psychologist;
    @ManyToOne
    private MentalDisease mentalDisease;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "diagnosis")
    private Set<Treatment> treatments;
    private Boolean onTherapy;

    /**
     * Empty constructor
     */
    public Diagnosis() {
        super();
    }

    public Diagnosis(Long diagnosisId, Date diagnosisDate, Date lastDiagnosisChangeDate, Patient patient, Psychologist psychologist, MentalDisease mentalDisease, Set<Treatment> treatments, Boolean onTherapy) {
        this.diagnosisId = diagnosisId;
        this.diagnosisDate = diagnosisDate;
        this.lastDiagnosisChangeDate = lastDiagnosisChangeDate;
        this.patient = patient;
        this.psychologist = psychologist;
        this.mentalDisease = mentalDisease;
        this.treatments = treatments;
        this.onTherapy = onTherapy;
    }

    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Date getLastDiagnosisChangeDate() {
        return lastDiagnosisChangeDate;
    }

    public void setLastDiagnosisChangeDate(Date lastDiagnosisChangeDate) {
        this.lastDiagnosisChangeDate = lastDiagnosisChangeDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    public MentalDisease getMentalDisease() {
        return mentalDisease;
    }

    public void setMentalDisease(MentalDisease mentalDisease) {
        this.mentalDisease = mentalDisease;
    }
    
    @XmlTransient
    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public Boolean getOnTherapy() {
        return onTherapy;
    }

    public void setOnTherapy(Boolean onTherapy) {
        this.onTherapy = onTherapy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.diagnosisId);
        hash = 67 * hash + Objects.hashCode(this.diagnosisDate);
        hash = 67 * hash + Objects.hashCode(this.lastDiagnosisChangeDate);
        hash = 67 * hash + Objects.hashCode(this.patient);
        hash = 67 * hash + Objects.hashCode(this.psychologist);
        hash = 67 * hash + Objects.hashCode(this.mentalDisease);
        hash = 67 * hash + Objects.hashCode(this.treatments);
        hash = 67 * hash + Objects.hashCode(this.onTherapy);
        return hash;
    }

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
        if (!Objects.equals(this.diagnosisId, other.diagnosisId)) {
            return false;
        }
        if (!Objects.equals(this.diagnosisDate, other.diagnosisDate)) {
            return false;
        }
        if (!Objects.equals(this.lastDiagnosisChangeDate, other.lastDiagnosisChangeDate)) {
            return false;
        }
        if (!Objects.equals(this.patient, other.patient)) {
            return false;
        }
        if (!Objects.equals(this.psychologist, other.psychologist)) {
            return false;
        }
        if (!Objects.equals(this.mentalDisease, other.mentalDisease)) {
            return false;
        }
        if (!Objects.equals(this.treatments, other.treatments)) {
            return false;
        }
        if (!Objects.equals(this.onTherapy, other.onTherapy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Diagnosis{" + "diagnosisId=" + diagnosisId + ", diagnosisDate=" + diagnosisDate + ", lastDiagnosisChangeDate=" + lastDiagnosisChangeDate + ", patient=" + patient + ", psychologist=" + psychologist + ", mentalDisease=" + mentalDisease + ", treatments=" + treatments + ", onTherapy=" + onTherapy + '}';
    }

}