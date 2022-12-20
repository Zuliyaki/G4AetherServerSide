package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name= "medication", schema= "aether" )
/*@NamedQueries({
    @NamedQuery(
            name = "getAllNotesByPatient", query = "SELECT dn FROM dailynote dn WHERE dn.user.id=:idUser"
    ),
    @NamedQuery(
            name = "createNewDailyNote", query = "INSERT INTO dailynote VALUES (:dnPatient, :dnNoteText, :noteComment, :dnNoteStatus, :dnNoteDate, :dnNoteDateLastEdited, :dnDayScore, :dnNoteReadable)"
    ),
    @NamedQuery(
            name = "modifyDailyNote", query = "UPDATE WHERE dn.dnPatient.id=:idPatient and dn.dailynote.id=:idNote"
    ),
    @NamedQuery(
            name = "deleteDailyNote", query = ""
    ),
    @NamedQuery(
            name = "addCommentOnDailyNote", query = ""
    )
*/
@XmlRootElement
public class Medication implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
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
    
    //HASHCODE
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.medicationName);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.typeOfMedication);
        hash = 29 * hash + Objects.hashCode(this.treatments);
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
        final Medication other = (Medication) obj;
        if (!Objects.equals(this.medicationName, other.medicationName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medication{" + "id=" + id + ", medicationName=" + medicationName + ", description=" + description + ", typeOfMedication=" + typeOfMedication + ", treatments=" + treatments + '}';
    }
    
        
    
}
