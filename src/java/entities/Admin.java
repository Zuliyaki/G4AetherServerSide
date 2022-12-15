package entities;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@DiscriminatorValue("admin")
@XmlRootElement
public class Admin extends User {
    
    @NotNull
    private Boolean admin;

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
     * @param admin
     * @param mentalDisease
     */
    public Admin(Boolean admin, Set<MentalDisease> mentalDisease) {
        this.admin = admin;
        this.mentalDisease = mentalDisease;
    }

    //Getters & Setters
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @XmlTransient
    public Set<MentalDisease> getMentalDisease() {
        return mentalDisease;
    }

    public void setMentalDisease(Set<MentalDisease> mentalDisease) {
        this.mentalDisease = mentalDisease;
    }

}
