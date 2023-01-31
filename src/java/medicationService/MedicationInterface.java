/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicationService;

import exceptions.MedicationNotFoundException;
import entities.Medication;
import java.util.List;

/**
 *
 * @author unaiz
 */
public interface MedicationInterface {

    public List<Medication> findAllMedication() throws MedicationNotFoundException;

    public Medication findMedicationById(Long medicationId) throws MedicationNotFoundException;
    
     public Medication findMedicationByName(String medicationName) throws MedicationNotFoundException;
}
