/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientService;

import entities.Patient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PatientException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import javax.crypto.Cipher;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaibAndLeire
 */
@Stateless
public class EJBPatientManager implements PatientInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;

    @Override
    public void createPatient(Patient patient) throws CreateException {
        try {
            MessageDigest messageDigest;
            String passwordResumen;
            byte[] decodedMessage = null;

            //Decrypt
            byte fileKey[] = fileReader(".\\Servidor\\PrivateKeyServidor.key");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte password[] = patient.getPassword().getBytes();
            decodedMessage = cipher.doFinal(password);

            //Hash
            messageDigest = MessageDigest.getInstance("SHA");
            byte dataBytes[] = decodedMessage;
            messageDigest.update(dataBytes);
            byte newPasswordResumen[] = messageDigest.digest();
            passwordResumen = new String(newPasswordResumen);

            patient.setPassword(passwordResumen);

            entityManager.persist(patient);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updatePatient(Patient patient) throws UpdateException {
        try {
            if (!entityManager.contains(patient)) {
                entityManager.merge(patient);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deletePatient(String dni) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(findPatientById(dni)));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    @Override
    public Patient findPatientById(String dni) throws PatientException {
        Patient patient;
        try {
            patient = (Patient) entityManager.find(Patient.class, dni);
        } catch (Exception e) {
            throw new PatientException(e.getMessage());
        }
        return patient;
    }

    @Override
    public List<Patient> findAllPatients() throws PatientException {
        List<Patient> patients = null;
        try {
            patients = entityManager.createNamedQuery("findAllPatients").getResultList();
        } catch (Exception e) {
            throw new PatientException(e.getMessage());
        }
        return patients;
    }

    @Override
    public List<Patient> findAllPatientsByPsychologist(String dniPsychologist) throws PatientException {
        List<Patient> patients = null;
        try {
            patients = entityManager.createNamedQuery("findAllPatientsByPsychologist").setParameter("dniPsychologist", dniPsychologist).getResultList();
        } catch (Exception e) {
            throw new PatientException(e.getMessage());
        }
        return patients;
    }

    @Override
    public void sendRecoveryEmail(Patient patient) throws PatientException {
        String newPassword = null;
        try {
            newPassword = emailRecovery.AetherEmailRecovery.sendEmail(patient.getEmail());
            newPassword = hashPassword.HashPassword.hashPassword(newPassword.getBytes());
            patient.setPassword(newPassword);
            updatePatient(patient);
        } catch (Exception e) {
            throw new PatientException(e.getMessage());
        }
    }

    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
