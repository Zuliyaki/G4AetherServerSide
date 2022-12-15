package entities;

import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admin", schema = "aether")
@DiscriminatorValue("admin")
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

    public Set<MentalDisease> getMentalDisease() {
        return mentalDisease;
    }

    public void setMentalDisease(Set<MentalDisease> mentalDisease) {
        this.mentalDisease = mentalDisease;
    }

}
