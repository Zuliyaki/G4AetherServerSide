/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagnosisService;

import exceptions.DiagnosisNotFoundException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.*;
import entities.Diagnosis;
import entities.Patient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author unaiz
 */
public interface DiagnosisInterface {
     public void createDiagnosis(Diagnosis diagnosis) throws CreateException;

    public void updateDiagnosis(Diagnosis diagnosis) throws UpdateException;

    public void deleteDiagnosis(Diagnosis diagnosis) throws DeleteException;
    
     public List<Diagnosis> findAllDiagnosis() throws DiagnosisNotFoundException;

    public Diagnosis findDiagnosisById(Long idDiagnosis) throws DiagnosisNotFoundException;

    public List<Diagnosis> findAllDiagnosisByPatient(String patientID) throws DiagnosisNotFoundException;
    
    public List<Diagnosis> findPatientDiagnosisByDate(String patientID, Date dateLow, Date dateGreat) throws DiagnosisNotFoundException;
    
    public List<Diagnosis> findAllIfPatientOnTeraphy(String patientID) throws DiagnosisNotFoundException;
    
    
}
