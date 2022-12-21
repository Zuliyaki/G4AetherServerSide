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

    private Long diagnosisId;
    private Long medicationId;

    @Enumerated(EnumType.STRING)
    private EnumDay day;

    @Enumerated(EnumType.STRING)
    private EnumDayTime dayTime;

    //CONSTRUCTOR
    public TreatmentId() {
    }

    public TreatmentId(Long diagnosisId, Long medicationId, EnumDay day, EnumDayTime dayTime) {
        this.diagnosisId = diagnosisId;
        this.medicationId = medicationId;
        this.day = day;
        this.dayTime = dayTime;
    }

    //GETTERS AND SETTERS
    public Long getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(Long diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
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
