/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MedicationService;

import entities.Diagnosis;
import entities.Medication;
import exceptions.MedicationNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaiz
 */

@Stateless
public class EJBMedicationManager implements MedicationInterface {

    /**
     * EntityManager for persistence unit.
     */
    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager em;

    @Override
    public List<Medication> findAllMedication() throws MedicationNotFoundException {
       List<Medication> medications;
        try{
            medications=em.createNamedQuery("findAllMedication").getResultList();
        }catch(Exception e){
            throw new MedicationNotFoundException(e.getMessage());
        }
        return medications;
    }

    @Override
    public Medication findMedicationById(Long medicationId) throws MedicationNotFoundException {
         Medication medication;
        try{
            medication=em.find(Medication.class, medicationId);
                
        }catch(Exception e){
            throw new MedicationNotFoundException(e.getMessage());
        }
        return medication;
    }

    @Override
    public Medication findMedicationByName(String medicationName) throws MedicationNotFoundException {
        Medication medication;
        try{
            medication=em.find(Medication.class, medicationName);
                
        }catch(Exception e){
            throw new MedicationNotFoundException(e.getMessage());
        }
        return medication;
    }

}
