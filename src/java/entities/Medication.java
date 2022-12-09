package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "medication", schema= "aether" )
public class Medication implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String medicationName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EnumMedType typeOfMedication;
    @OneToMany(mappedBy = "medication")
    private Set <Treatment> treatments;
    
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
    public Medication(Integer id, String medicationName, String description, EnumMedType typeOfMedication) {
        this.id = id;
        this.medicationName = medicationName;
        this.description = description;
        this.typeOfMedication = typeOfMedication;
    }

    //Getters & Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
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
