/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientService;


import emailRecovery.AetherEmailRecovery;
import entities.Patient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PatientException;
import hashPassword.HashPassword;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.PatientFacadeREST;

/**
 *
 * @author unaibAndLeire
 */
@Stateless
public class EJBPatientManager implements PatientInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;
    private static final Logger LOGGER = Logger.getLogger(EJBPatientManager.class.getName());

    @Override
    public void createPatient(Patient patient) throws CreateException {
        
        /*
     
        byte[] decodedMessage = null;
        String passwordResumen;
        byte [] key = null;
        

        InputStream is = getClass().getResourceAsStream("PrivateKeyServidor.key");
     
        byte fileKey[] = null;
        try {
            fileKey = new byte[is.available()];
            is.read(fileKey,0,is.available());
            key = fileKey;
        } catch (IOException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        PKCS8EncodedKeySpec PKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
        PrivateKey privateKey = null;
        try {
            privateKey = keyFactory.generatePrivate(PKCS8EncodedKeySpec);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //Hash
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EJBPatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        patient.setPassword(cifrarTextoHash(patient.getPassword()));;
        

        entityManager.persist(patient);

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
        AetherEmailRecovery  recover= new AetherEmailRecovery();
        try {
            newPassword = recover.sendEmail(patient.getEmail());
            newPassword = HashPassword.hashPassword(newPassword.getBytes());
            
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
    
     public String cifrarTextoHash(String texto) {
        final MessageDigest messageDigest;
        String sResumen = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            //**********************************************************************************************************************************************
            //FALTA CÓDIGO 
            byte dataBytes[] = messageDigest.digest(texto.getBytes(StandardCharsets.UTF_8));
            messageDigest.update(dataBytes);
            //**********************************************************************************************************************************************
            byte resumen[] = messageDigest.digest();// Se calcula el resumen
            
            sResumen = Hexadecimal(resumen);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sResumen;
    }

    static String Hexadecimal(byte[] resumen) {
        String HEX = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }
}
