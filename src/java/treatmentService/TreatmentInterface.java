/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treatmentService;

import entities.Diagnosis;
import entities.EnumDay;
import entities.EnumDayTime;
import exceptions.TreatmentNotFoundException;
import entities.Treatment;
import entities.TreatmentId;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author unaiz
 */
public interface TreatmentInterface {

    public void createTreatment(Treatment treament) throws CreateException;

    public void updateTreatment(Treatment treament) throws UpdateException;

    public void deleteTreatment(Treatment treament) throws DeleteException;

    public Treatment findTreatmentByID(TreatmentId treatmentid) throws TreatmentNotFoundException;

    public List<Treatment> findTreatmentsByDiagnosisId(Long DiagnosisId) throws TreatmentNotFoundException;
}
