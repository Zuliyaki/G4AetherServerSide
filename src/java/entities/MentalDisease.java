package entities;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "mentaldisease", schema = "aether")
@NamedQueries({
    @NamedQuery(
            name = "getAllMentalDiseases", query = "SELECT * FROM mentaldisease md"
    )
    ,
    @NamedQuery(
            name = "getAllMentalDiseasesOrderByName", query = "SELECT * FROM mentaldisease md ORDER BY md.mdname=:name ASC"
    )
    , 
    @NamedQuery(
            name = "getMentalDiseasesByName", query = "SELECT md FROM mentaldisease md WHERE md.mdname=:name"
    )
    , 
    @NamedQuery(
            name = "getMentalDiseasesById", query = "SELECT md FROM mentaldisease md WHERE md.idMentalDisease=:idMentalDisease"
    )

})
@XmlRootElement
public class MentalDisease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMentalDisease;

    @ManyToOne
    private Admin mdadmin;

    @Enumerated(EnumType.STRING)
    private EnumMentalDisease mdType;

    @Column(name = "name")
    private String mdName;

    @Column(name = "description")
    private String mdDescription;

    @Column(name = "symptons")
    private String mdSympton;

    @Temporal(TemporalType.DATE)
    private Date mdAddDate;

    @OneToMany(mappedBy = "mentalDisease")
    private Set<Diagnosis> diagnosis;

    /**
     * Empty constructor
     */
    public MentalDisease() {
    }

    /**
     * Costructor with parameters
     *
     * @param idMentalDisease
     * @param mdadmin
     * @param mdType
     * @param mdName
     * @param mdDescription
     * @param mdSympton
     * @param mdAddDate
     * @param diagnosis
     */
    public MentalDisease(Long idMentalDisease, Admin mdadmin, EnumMentalDisease mdType, String mdName, String mdDescription, String mdSympton, Date mdAddDate, Set<Diagnosis> diagnosis) {
        this.idMentalDisease = idMentalDisease;
        this.mdadmin = mdadmin;
        this.mdType = mdType;
        this.mdName = mdName;
        this.mdDescription = mdDescription;
        this.mdSympton = mdSympton;
        this.mdAddDate = mdAddDate;
        this.diagnosis = diagnosis;
    }

    //Getters & Setters
    public Long getIdMentalDisease() {
        return idMentalDisease;
    }

    public void setIdMentalDisease(Long idMentalDisease) {
        this.idMentalDisease = idMentalDisease;
    }

    public Admin getAdmin() {
        return mdadmin;
    }

    public void setAdmin(Admin mdadmin) {
        this.mdadmin = mdadmin;
    }

    public EnumMentalDisease getMdType() {
        return mdType;
    }

    public void setMdType(EnumMentalDisease mdType) {
        this.mdType = mdType;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdDescription() {
        return mdDescription;
    }

    public void setMdDescription(String mdDescription) {
        this.mdDescription = mdDescription;
    }

    public String getMdSympton() {
        return mdSympton;
    }

    public void setMdSympton(String mdSympton) {
        this.mdSympton = mdSympton;
    }

    public Date getMdAddDate() {
        return mdAddDate;
    }

    public void setMdAddDate(Date mdAddDate) {
        this.mdAddDate = mdAddDate;
    }

    @XmlTransient
    public Set<Diagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Set<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

}
