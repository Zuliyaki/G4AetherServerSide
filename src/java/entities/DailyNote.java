package entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;

@Entity
//@Table(name = "dailyNotes")
public class DailyNote implements Serializable {

    private Patient patient;
    private String noteText;
    private String noteComment;
    private Status noteStatus;
    private Date noteDate;
    private Date noteDateLastEdited;
    private Double dayScore;
    private Boolean noteReadable;

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
     *
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     *
     * @return
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     *
     * @param noteText
     */
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    /**
     *
     * @return
     */
    public String getNoteText() {
        return noteText;
    }

    /**
     *
     * @param noteComent
     */
    public void setNoteComent(String noteComent) {
        this.noteComment = noteComent;
    }
    
    /**
     *
     * @return
     */
    public String getNoteComent() {
        return noteComment;
    }
    
    /**
     *
     * @param noteStatus
     */
    public void setNoteStatus(Status noteStatus) {
        this.noteStatus = noteStatus;
    }

    /**
     *
     * @return
     */
    public Status getNoteStatus() {
        return noteStatus;
    }

    /**
     * @param noteDate
     */
    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    /**
     *
     * @return
     */
    public Date getNoteDate() {
        return noteDate;
    }
    
    /**
     *
     * @param noteDateLastEdited
     */
    public void setNoteDateLastEdited(Date noteDateLastEdited) {
        this.noteDateLastEdited = noteDateLastEdited;
    }

    /**
     *
     * @return
     */
    public Date getNoteDateLastEdited() {
        return noteDateLastEdited;
    }
    
    /**
     *
     * @param dayScore
     */
    public void setDayScore(Double dayScore) {
        this.dayScore = dayScore;
    }

    /**
     *
     * @return
     */
    public Double getDayScore() {
        return dayScore;
    }

    /**
     *
     * @param noteReadable
     */
    public void setNoteReadable(Boolean noteReadable) {
        this.noteReadable = noteReadable;
    }

    /**
     *
     * @return
     */
    public Boolean getNoteReadable() {
        return noteReadable;
    }
}
