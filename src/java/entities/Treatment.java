package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name= "treatment", schema= "aether" )
public class Treatment implements Serializable{
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TreatmentId treatmentId;
    @ManyToOne
    @MapsId("diagnosisId")
    private Diagnosis diagnosis;
    @MapsId("medicationId")
    @ManyToOne
    private Medication medication;
    @Enumerated(EnumType.STRING)
    private EnumDay day;
    @Enumerated(EnumType.STRING)
    private EnumDayTime dayTime;
    
    /**
     * Empty constructor
     */
    public Treatment() {
        super();
    }

    /**
     *Costructor with parameters
     *
     * @param diagnosis
     * @param medication
     * @param day
     * @param dayTime
     */
    public Treatment(Diagnosis diagnosis, Medication medication, EnumDay day, EnumDayTime dayTime) {
        this.diagnosis = diagnosis;
        this.medication = medication;
        this.day = day;
        this.dayTime = dayTime;
    }

    //Getters & Setters
    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setDay(EnumDay day) {
        this.day = day;
    }

    public EnumDay getDay() {
        return day;
    }

    public void setDayTime(EnumDayTime dayTime) {
        this.dayTime = dayTime;
    }

    public EnumDayTime getDayTime() {
        return dayTime;
    }
    //HASHCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.treatmentId);
        hash = 29 * hash + Objects.hashCode(this.diagnosis);
        hash = 29 * hash + Objects.hashCode(this.medication);
        hash = 29 * hash + Objects.hashCode(this.day);
        hash = 29 * hash + Objects.hashCode(this.dayTime);
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
        final Treatment other = (Treatment) obj;
        if (!Objects.equals(this.treatmentId, other.treatmentId)) {
            return false;
        }
        return true;
    }
    // to string
    @Override
    public String toString() {
        return "Treatment{" + "treatmentId=" + treatmentId + ", diagnosis=" + diagnosis + ", medication=" + medication + ", day=" + day + ", dayTime=" + dayTime + '}';
    }
    
    
    
}
