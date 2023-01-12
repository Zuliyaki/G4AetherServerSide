package entities;

import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author unaibAndLeire
 */
@Entity
@DiscriminatorValue("admin")
@XmlRootElement
public class Admin extends User {
    @OneToMany(mappedBy = "mdadmin")
    private Set<MentalDisease> mentalDisease;

    /**
     * Empty constructor
     */
    public Admin() {
    }

    /**
     * Costructor with parameters
     *
     * @param mentalDisease
     */
    public Admin(Set<MentalDisease> mentalDisease) {

        this.mentalDisease = mentalDisease;
    }

    //Getters & Setters
    @XmlTransient
    public Set<MentalDisease> getMentalDisease() {
        return mentalDisease;
    }

    public void setMentalDisease(Set<MentalDisease> mentalDisease) {
        this.mentalDisease = mentalDisease;
    }

}
