package entities;

import java.io.Serializable;

public class Medication implements Serializable{
    private String id;
    private String medicationName;
    private String description;
    private EnumMedType typeOfMedication;
    
    /**
     * Empty constructor
     */
    public Medication() {
        super();
    }

    /**
     * Costructor with parameters
     *
     * @param id
     * @param medicationName
     * @param description
     * @param typeOfMedication
     */
    public Medication(String id, String medicationName, String description, EnumMedType typeOfMedication) {
        this.id = id;
        this.medicationName = medicationName;
        this.description = description;
        this.typeOfMedication = typeOfMedication;
    }

    //Getters & Setters
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTypeOfMedication(EnumMedType typeOfMedication) {
        this.typeOfMedication = typeOfMedication;
    }

    public EnumMedType getTypeOfMedication() {
        return typeOfMedication;
    }
}
