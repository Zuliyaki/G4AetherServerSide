package entities;

import java.io.Serializable;
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
}
