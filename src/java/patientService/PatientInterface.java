/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientService;

import java.util.List;
import entities.Patient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PatientException;

/**
 *
 * @author unaibAndLeire
 */
public interface PatientInterface {

    public void createPatient(Patient patient) throws CreateException;

    public void updatePatient(Patient patient, String dni) throws UpdateException;

    public void deletePatient(String dni) throws DeleteException;

    public Patient findPatientById(String dni) throws PatientException;

    public List<Patient> findAllPatients() throws PatientException;

    public List<Patient> findAllPatientsByPsychologist(String dniPsychologist) throws PatientException;

}
