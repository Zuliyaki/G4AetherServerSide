/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiagnosisService;

import entities.Diagnosis;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.DiagnosisNotFoundException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaiz
 */

@Stateless
public class EJBDiagnosisManager implements DiagnosisInterface {
   /**
     * EntityManager for persistence unit.
     */
    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager em;
    
    
    
    @Override
    public void createDiagnosis(Diagnosis diagnosis) throws CreateException {
        try{
            
            em.persist(diagnosis);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateDiagnosis(Diagnosis diagnosis) throws UpdateException {
         try{
            if(!em.contains(diagnosis))
                em.merge(diagnosis);
            em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deleteDiagnosis(Diagnosis diagnosis) throws DeleteException {
          try{
            em.remove(em.merge(diagnosis));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public List<Diagnosis> findAllDiagnosis() throws DiagnosisNotFoundException {
         List<Diagnosis> diagnosises;
        try{
            diagnosises=em.createNamedQuery("findAllDiagnosis").getResultList();
        }catch(Exception e){
            throw new DiagnosisNotFoundException(e.getMessage());
        }
        return diagnosises;
    }
    

    @Override
    public Diagnosis findDiagnosisById(Long idDiagnosis) throws DiagnosisNotFoundException {
        Diagnosis diagnosis;
        try{
            diagnosis=em.find(Diagnosis.class, idDiagnosis);
        }catch(Exception e){
            throw new DiagnosisNotFoundException(e.getMessage());
        }
        return diagnosis;
    }

    @Override
    public List<Diagnosis> findAllDiagnosisByPatient(String patientID) throws DiagnosisNotFoundException {
         List<Diagnosis> diagnosises;
        try{
           diagnosises=em.createNamedQuery("findAllDiagnosisByPatient").setParameter("patient", patientID).getResultList();
        }catch(Exception e){
            throw new DiagnosisNotFoundException(e.getMessage());
        }
        return diagnosises;
    }

    @Override
    public List<Diagnosis> findPatientDiagnosisByDate(String patientID, Date dateLow, Date dateGreat) throws DiagnosisNotFoundException {
        List<Diagnosis> diagnosises;
        try{
            diagnosises = em.createNamedQuery("findPatientDiagnosisByDate").setParameter("diaDateGreat", dateGreat).setParameter("diaDateLow", dateLow).setParameter("patient",patientID).getResultList();
        }catch(Exception e){
            throw new DiagnosisNotFoundException(e.getMessage());
        }
        return diagnosises;
    }

    @Override
    public List<Diagnosis> findAllIfPatientOnTeraphy(String patientID) throws DiagnosisNotFoundException {
        List<Diagnosis> diagnosises;
        try{
            diagnosises=em.createNamedQuery("findAllIfPatientOnTeraphy").setParameter("patient", patientID).getResultList();
        }catch(Exception e){
            throw new DiagnosisNotFoundException(e.getMessage());
        }
        return diagnosises;
    }
    
    
}
