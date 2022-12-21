/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 2dam
 */
@Embeddable
public class TreatmentId implements Serializable {

    private Integer diagnosisId;
    private Integer medicationId;
    
    @Enumerated(EnumType.STRING)
    private EnumDay day;

    @Enumerated(EnumType.STRING)
    private EnumDayTime dayTime;

    
    //CONSTRUCTOR

    public TreatmentId() {
    }
    
    
    
    public TreatmentId(Integer diagnosisId, Integer medicationId, EnumDay day, EnumDayTime dayTime) {
        this.diagnosisId = diagnosisId;
        this.medicationId = medicationId;
        this.day = day;
        this.dayTime = dayTime;
    }

  

    //GETTERS AND SETTERS
    public Integer getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Integer diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public EnumDay getDay() {
        return day;
    }

    public void setDay(EnumDay day) {
        this.day = day;
    }

    public EnumDayTime getDayTime() {
        return dayTime;
    }

    public void setDayTime(EnumDayTime dayTime) {
        this.dayTime = dayTime;
    }

    //HASHCODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.diagnosisId);
        hash = 79 * hash + Objects.hashCode(this.medicationId);
        return hash;
    }

    //EQUALS
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TreatmentId other = (TreatmentId) obj;
        if (!Objects.equals(this.diagnosisId, other.diagnosisId)) {
            return false;
        }
        if (!Objects.equals(this.medicationId, other.medicationId)) {
            return false;
        }
        return true;
    }

}
