/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treatmentService;

import entities.Diagnosis;
import entities.EnumDay;
import entities.EnumDayTime;
import entities.Medication;
import entities.Treatment;
import entities.TreatmentId;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.TreatmentNotFoundException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaiz
 */
@Stateless
public class EJBTreatmentManager implements TreatmentInterface {

    /**
     * EntityManager for persistence unit.
     */
    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager em;

    @Override
    public void createTreatment(Treatment treament) throws CreateException {
        try {
          if (!em.contains(treament.getDiagnosis())) 
                 treament.setDiagnosis(em.merge(treament.getDiagnosis()));
            
           if (!em.contains(treament.getMedication())) 
                treament.setMedication(em.merge(treament.getMedication()));
            
            em.persist(treament);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateTreatment(Treatment treament) throws UpdateException {
        try {
            if (!em.contains(treament)) {
                em.merge(treament);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deleteTreatment(Treatment treament) throws DeleteException {
        try {
            em.remove(em.merge(treament));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Treatment findTreatmentByID(TreatmentId treatmentid ) throws TreatmentNotFoundException {
        Treatment treatment = new Treatment();
        try {
            
            em.find(Treatment.class, treatmentid);
        } catch (Exception e) {
            throw new TreatmentNotFoundException(e.getMessage());
        }
        return treatment;
    }

    @Override
    public List<Treatment> findTreatmentsByDiagnosisId(Long DiagnosisId) throws TreatmentNotFoundException {
        List<Treatment> treatments;
        try {
            treatments = em.createNamedQuery("findTreatmentsByDiagnosisId")
                        .setParameter("diagnosis", em.find(Diagnosis.class, DiagnosisId))
                        .getResultList();

        } catch (Exception e) {
            throw new TreatmentNotFoundException(e.getMessage());
        }
        return treatments;
    }

    @Override
    public List<Treatment> findAllTreatments() throws TreatmentNotFoundException {
          List<Treatment> treatments;
        try {
            treatments = em.createNamedQuery("findAllTreatments").getResultList();
                     

        } catch (Exception e) {
            throw new TreatmentNotFoundException(e.getMessage());
        }
        return treatments;
    }
}
