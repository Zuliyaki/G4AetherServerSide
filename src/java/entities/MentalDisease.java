package packages;

import java.io.Serializable;

import java.time.LocalDate;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class MentalDisease implements Serializable{
    private EnumMentalDisease mdType;
    private String mdName;
    private String mdDescription;
    private String mdSympton;
    private Admin admin;
    private Date mdAddDate;

    /**
     * Empty constructor
     */
    public MentalDisease() {
    }
    
    /**
     *Costructor with parameters
     *
     * @param mdType
     * @param mdName
     * @param mdDescription
     * @param mdSympton
     * @param admin
     * @param mdAddDate
     */
    public MentalDisease(EnumMentalDisease mdType, String mdName, String mdDescription, String mdSympton, Admin admin,
                         Date mdAddDate) {
        this.mdType = mdType;
        this.mdName = mdName;
        this.mdDescription = mdDescription;
        this.mdSympton = mdSympton;
        this.admin = admin;
        this.mdAddDate = mdAddDate;
    }

    //Getters & Setters
    public void setMdType(EnumMentalDisease mdType) {
        this.mdType = mdType;
    }

    public EnumMentalDisease getMdType() {
        return mdType;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdDescription(String mdDescription) {
        this.mdDescription = mdDescription;
    }

    public String getMdDescription() {
        return mdDescription;
    }

    public void setMdSympton(String mdSympton) {
        this.mdSympton = mdSympton;
    }

    public String getMdSympton() {
        return mdSympton;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setMdAddDate(Date mdAddDate) {
        this.mdAddDate = mdAddDate;
    }

    public Date getMdAddDate() {
        return mdAddDate;
    }
}
