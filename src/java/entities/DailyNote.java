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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author unaib
 */
@Entity
@Table(name = "dailynotes", schema = "aether")
public class DailyNote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Daily note id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Patient that wrote the note
     */
    @ManyToOne
    private Patient patient;
    /**
     * Content of the note
     */
    private String noteText;
    /**
     * Comment the psychologist can do about the note
     */
    private String noteComment;
    /**
     * Enum for the note if was readen or not
     */
    @Enumerated(EnumType.STRING)
    private Status noteStatus;
    /**
     * Date of creation of the note
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteDate;
    /**
     * Date of the last time the note was edited
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date noteDateLastEdited;
    /**
     * Score of the day [1-100] depending how it was
     */
    private Double dayScore;
    /**
     * Lets the patient choose if the psychologist can read the note
     */
    private Boolean noteReadable;

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
    public DailyNote(Patient patient, Status noteStatus, Date noteDate, Date noteDateLastEdited,
            Double dayScore, Boolean noteReadable) {
        this.patient = patient;
        this.noteStatus = noteStatus;
        this.noteDate = noteDate;
        this.noteDateLastEdited = noteDateLastEdited;
        this.dayScore = dayScore;
        this.noteReadable = noteReadable;
    }

    //Getters & Setters
    /**
     * Sets the patient
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Get the patient
     *
     * @return
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the note text
     *
     * @param noteText
     */
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    /**
     * Gets the note text
     *
     * @return
     */
    public String getNoteText() {
        return noteText;
    }

    /**
     * Sets the note coment
     *
     * @param noteComent
     */
    public void setNoteComent(String noteComent) {
        this.noteComment = noteComent;
    }

    /**
     * Gets the note coment
     *
     * @return
     */
    public String getNoteComent() {
        return noteComment;
    }

    /**
     * Sets the note status
     *
     * @param noteStatus
     */
    public void setNoteStatus(Status noteStatus) {
        this.noteStatus = noteStatus;
    }

    /**
     * Gets the note status
     *
     * @return
     */
    public Status getNoteStatus() {
        return noteStatus;
    }

    /**
     * Sets the note date
     *
     * @param noteDate
     */
    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    /**
     * Gets the note date
     *
     * @return
     */
    public Date getNoteDate() {
        return noteDate;
    }

    /**
     * Sets the last time the note was edited
     *
     * @param noteDateLastEdited
     */
    public void setNoteDateLastEdited(Date noteDateLastEdited) {
        this.noteDateLastEdited = noteDateLastEdited;
    }

    /**
     * Gets the last time the note was edited
     *
     * @return
     */
    public Date getNoteDateLastEdited() {
        return noteDateLastEdited;
    }

    /**
     * Sets the day score
     *
     * @param dayScore
     */
    public void setDayScore(Double dayScore) {
        this.dayScore = dayScore;
    }

    /**
     * Gets the day score
     *
     * @return
     */
    public Double getDayScore() {
        return dayScore;
    }

    /**
     * Sets if the note is readable by the psychologist
     *
     * @param noteReadable
     */
    public void setNoteReadable(Boolean noteReadable) {
        this.noteReadable = noteReadable;
    }

    /**
     * Gets if the note is readable by the psychologist
     *
     * @return
     */
    public Boolean getNoteReadable() {
        return noteReadable;
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
        hash = 73 * hash + Objects.hashCode(this.noteDate);
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
        if (!Objects.equals(this.noteDate, other.noteDate)) {
            return false;
        }
        return true;
    }

}
