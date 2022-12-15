package entities;

import java.io.Serializable;

import java.time.LocalDate;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class User implements Serializable {

    @Id
    @NotNull
    private String dni;
    @NotNull
    private String fullName;
    @NotNull
    private Date birthDate;
    @NotNull
    private String password;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    private String email;

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * Costructor with parameters
     *
     * @param dni
     * @param fullName
     * @param birthDate
     * @param password
     * @param phoneNumber
     * @param email
     */
    public User(String dni, String fullName, Date birthDate, String password, Integer phoneNumber, String email) {
        this.dni = dni;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //Getters & Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
