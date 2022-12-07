package packages;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
    private Boolean admin;

    /**
     * Empty constructor
     */
    public Admin() {
    }

    /**
     * Costructor with parameters
     *
     * @param admin
     */
    public Admin(Boolean admin) {
        super();
        this.admin = admin;
    }
    
    //Getters & Setters
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getAdmin() {
        return admin;
    }
}
