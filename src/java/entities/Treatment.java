package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "treatment", schema = "aether")
@NamedQueries({
    @NamedQuery(
            name = "findAllTreatments", query = "SELECT tr FROM Treatment tr"
    )
    ,
    @NamedQuery(
            name = "findTreatmentsByDiagnosisId", query = "SELECT tr FROM Treatment tr WHERE tr.diagnosis=:diagnosis"
    )
})
@XmlRootElement
public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TreatmentId treatmentId;
    
    @ManyToOne(targetEntity=Diagnosis.class)
    @MapsId("diagnosisId")
    private Diagnosis diagnosis;

    @ManyToOne(targetEntity= Medication.class)
    @MapsId("medicationId")
    private Medication medication;

    
    public Treatment() {
    }

    public Treatment(TreatmentId treatmentId, Diagnosis diagnosis, Medication medication) {
        this.treatmentId = treatmentId;
        this.diagnosis = diagnosis;
        this.medication = medication;
    }

    public TreatmentId getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(TreatmentId treatmentId) {
        this.treatmentId = treatmentId;
    }
    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }
     
    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.treatmentId);
        hash = 37 * hash + Objects.hashCode(this.diagnosis);
        hash = 37 * hash + Objects.hashCode(this.medication);
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
        final Treatment other = (Treatment) obj;
        if (!Objects.equals(this.treatmentId, other.treatmentId)) {
            return false;
        }
        return true;
    }

}
