package entities;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Patient extends User {
     
     
    private String mbti;
    private Psychologist psychologist;
    
    /**
     * Collection of all notes
     */
    @OneToMany(mappedBy = "id")
    private Set<DailyNote> dailyNotes;

    //Cosntructor
    /**
     * 1
     * Empty constructor
     */
   
    public Patient() {
        
 
    }

    /**
     * Costructor with parameters
     *
     * @param mbti
     * @param psychologist
     * @param dailyNotes
     */
    public Patient(String mbti, Psychologist psychologist, Set<DailyNote> dailyNotes) {
        this.mbti = mbti;
        this.psychologist = psychologist;
        this.dailyNotes = dailyNotes;
    }

    //Getters & Setters
    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getMbti() {
        return mbti;
    }

    public void setPsychologist(Psychologist psychologist) {
        this.psychologist = psychologist;
    }

    public Psychologist getPsychologist() {
        return psychologist;
    }

    public Set<DailyNote> getDailyNotes() {
        return dailyNotes;
    }

    public void setDailyNotes(Set<DailyNote> dailyNotes) {
        this.dailyNotes = dailyNotes;
    }

}
