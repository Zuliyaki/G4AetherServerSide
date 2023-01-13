package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "medication", schema = "aether")
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
public class Medication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicationId;
    private String medicationName;
    private String description;
    @Enumerated(EnumType.STRING)
    private EnumMedType typeOfMedication;
    @OneToMany(mappedBy = "medication")
    private Set<Treatment> treatments;

    /**
     * Empty constructor
     */
    public Medication() {
        super();
    }

    public Medication(Long medicationId, String medicationName, String description, EnumMedType typeOfMedication, Set<Treatment> treatments) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.description = description;
        this.typeOfMedication = typeOfMedication;
        this.treatments = treatments;
    }
    //Getters & Setters

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EnumMedType getTypeOfMedication() {
        return typeOfMedication;
    }

    public void setTypeOfMedication(EnumMedType typeOfMedication) {
        this.typeOfMedication = typeOfMedication;
    }

    @XmlTransient
    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

}
