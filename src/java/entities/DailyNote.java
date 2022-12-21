package entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author unaib
 */
@Entity
@Table(name = "dailynote", schema = "aether")
@NamedQueries({
    @NamedQuery(
        name = "getAllNotesByPatient", query = "SELECT dn FROM DailyNote dn, User us WHERE us.dni=:idUser"
    )
})/*
    ,
    @NamedQuery(
            name = "createNewDailyNote", query = "INSERT INTO DailyNote(dnPatient, dnNoteText, noteComment, dnNoteStatus, dnNoteDate, dnNoteDateLastEdited, dnDayScore, dnNoteReadable) SELECT :dnPatient, :dnNoteText, :noteComment, :dnNoteStatus, :dnNoteDate, :dnNoteDateLastEdited, :dnDayScore, :dnNoteReadable"
    )
    ,
    @NamedQuery(
            name = "modifyDailyNote", query = "UPDATE DailyNote dn SET dn.dnNoteText WHERE dn.dnPatient.id=:idPatient and dn.dailynote.id=:idNote"
    )
    ,
    @NamedQuery(
            name = "deleteDailyNote", query = "DELETE FROM DailyNote dn WHERE dn.id=:idDailyNote"
    )
    ,
    @NamedQuery(
            name = "addCommentOnDailyNote", query = "UPDATE DailyNote dn SET dn.dnNoteComment=:noteComent WHERE dn.id=:idDailyNote"
    )
})*/
@XmlRootElement
public class DailyNote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Auto generated daily note id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Patient that wrote the note
     */
    @ManyToOne
    @NotNull
    private Patient dnPatient;
    /**
     * Content of the note
     */
    @NotNull
    private String dnNoteText;
    /**
     * Comment the psychologist can do about the note
     */
    private String dnNoteComment;
    /**
     * Enum for the note if was readen or not
     */
    @Enumerated(EnumType.STRING)
    private EnumReadedStatus dnNoteStatus;
    /**
     * Date of creation of the note
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dnNoteDate;
    /**
     * Date of the last time the note was edited
     */
    @Temporal(TemporalType.DATE)
    private Date dnNoteDateLastEdited;
    /**
     * Score of the day [1-100] depending how it was
     */
    @NotNull
    private Double dnDayScore;
    /**
     * Lets the dnPatient choose if the psychologist can read the note
     */
    private Boolean dnNoteReadable;

    //Costructors
    /**
     * Empty constructor
     */
    public DailyNote() {
    }

    /**
     * Constructor with params
     *
     * @param patient
     * @param noteStatus
     * @param noteDate
     * @param noteDateLastEdited
     * @param dayScore
     * @param noteReadable
     */
    public DailyNote(Patient patient, EnumReadedStatus noteStatus, Date noteDate, Date noteDateLastEdited,
            Double dayScore, Boolean noteReadable) {
        this.dnPatient = patient;
        this.dnNoteStatus = noteStatus;
        this.dnNoteDate = noteDate;
        this.dnNoteDateLastEdited = noteDateLastEdited;
        this.dnDayScore = dayScore;
        this.dnNoteReadable = noteReadable;
    }

    //Getters & Setters
    /**
     * Sets the dnPatient
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.dnPatient = patient;
    }

    /**
     * Get the dnPatient
     *
     * @return
     */
    public Patient getPatient() {
        return dnPatient;
    }

    /**
     * Sets the note text
     *
     * @param noteText
     */
    public void setNoteText(String noteText) {
        this.dnNoteText = noteText;
    }

    /**
     * Gets the note text
     *
     * @return
     */
    public String getNoteText() {
        return dnNoteText;
    }

    /**
     * Sets the note coment
     *
     * @param noteComent
     */
    public void setNoteComent(String noteComent) {
        this.dnNoteComment = noteComent;
    }

    /**
     * Gets the note coment
     *
     * @return
     */
    public String getNoteComent() {
        return dnNoteComment;
    }

    /**
     * Sets the note status
     *
     * @param noteStatus
     */
    public void setNoteStatus(EnumReadedStatus noteStatus) {
        this.dnNoteStatus = noteStatus;
    }

    /**
     * Gets the note status
     *
     * @return
     */
    public EnumReadedStatus getNoteStatus() {
        return dnNoteStatus;
    }

    /**
     * Sets the note date
     *
     * @param noteDate
     */
    public void setNoteDate(Date noteDate) {
        this.dnNoteDate = noteDate;
    }

    /**
     * Gets the note date
     *
     * @return
     */
    public Date getNoteDate() {
        return dnNoteDate;
    }

    /**
     * Sets the last time the note was edited
     *
     * @param noteDateLastEdited
     */
    public void setNoteDateLastEdited(Date noteDateLastEdited) {
        this.dnNoteDateLastEdited = noteDateLastEdited;
    }

    /**
     * Gets the last time the note was edited
     *
     * @return
     */
    public Date getNoteDateLastEdited() {
        return dnNoteDateLastEdited;
    }

    /**
     * Sets the day score
     *
     * @param dayScore
     */
    public void setDayScore(Double dayScore) {
        this.dnDayScore = dayScore;
    }

    /**
     * Gets the day score
     *
     * @return
     */
    public Double getDayScore() {
        return dnDayScore;
    }

    /**
     * Sets if the note is readable by the psychologist
     *
     * @param noteReadable
     */
    public void setNoteReadable(Boolean noteReadable) {
        this.dnNoteReadable = noteReadable;
    }

    /**
     * Gets if the note is readable by the psychologist
     *
     * @return
     */
    public Boolean getNoteReadable() {
        return dnNoteReadable;
    }

    /**
     * Sets the note id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the note id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.dnNoteDate);
        return hash;
    }

    /**
     * Checks if two DailyNotes are the same
     *
     * @param obj
     * @return Return if the two objects are the same
     */
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
        final DailyNote other = (DailyNote) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dnNoteDate, other.dnNoteDate)) {
            return false;
        }
        return true;
    }

}
