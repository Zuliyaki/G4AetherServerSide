package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "treatment", schema = "aether")
@XmlRootElement
public class Treatment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @NotNull
    private TreatmentId treatmentId;
    
    @ManyToOne
    @NotNull
    @MapsId("diagnosisId")
    private Diagnosis diagnosis;

    @ManyToOne
    @NotNull
    @MapsId("medicationId")
    private Medication medication;

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
    


    /**
     * Empty constructor
     */
  

}
