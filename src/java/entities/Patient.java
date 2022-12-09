package entities;

import java.util.Set;
import javax.persistence.*;


 @Entity
public class Patient extends User {
     
     
    private String mbti;
    private Psychologist psychologist;
    
    @OneToMany(mappedBy = "patient")
    private Set <Diagnosis> diagnoses;

    /**
     * Empty constructor
     */
   
    public Patient() {
        
 
    }

    /**
     *Costructor with parameters
     *
     * @param mbti
     * @param psychologist
     */
    public Patient(String mbti, Psychologist psychologist) {
        this.mbti = mbti;
        this.psychologist = psychologist;
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
}
