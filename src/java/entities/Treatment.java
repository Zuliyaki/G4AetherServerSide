package entities;

public class Treatment {
    private Diagnosis diagnosis;
    private Medication medication;
    private EnumDay day;
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
