package entities;

import javax.persistence.Entity;;

@Entity
public class Psychologist extends User{
    private String titulation;
    private String invitationCode;
    
    /**
     * Empty constructor
     */
    public Psychologist() {
        super();
    }

    /**
     * Costructor with parameters
     *
     * @param admin
     */
    public Psychologist(String titulation, String invitationCode) {
        super();
        this.titulation = titulation;
        this.invitationCode = invitationCode;
    }

    //Getters & Setters
    public void setTitulation(String titulation) {
        this.titulation = titulation;
    }

    public String getTitulation() {
        return titulation;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }
}
