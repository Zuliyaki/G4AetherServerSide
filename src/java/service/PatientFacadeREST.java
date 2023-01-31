/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Patient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PatientException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import patientService.PatientInterface;

/**
 *
 * @author unaibAndLeire
 */
@Path("entities.patient")
public class PatientFacadeREST {

    @EJB
    private PatientInterface patientEJB;

    private static final Logger LOGGER = Logger.getLogger(PatientFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createPatient(Patient entity) {
        try {
            patientEJB.createPatient(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editPatient(Patient entity) {
        try {
            patientEJB.updatePatient(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{dni}")
    public void removePatient(@PathParam("dni") String dni) {
        try {
            patientEJB.deletePatient(dni);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Path("sendRecoveryEmail")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void sendRecoveryEmail(Patient entity) {
        try {
            patientEJB.sendRecoveryEmail(entity);
        } catch (PatientException ex) {
            Logger.getLogger(PatientFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Patient> findAllPatients() {
        List<Patient> patients = null;
        try {
            patients = patientEJB.findAllPatients();
        } catch (PatientException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return patients;
    }

    @GET
    @Path("findPatientsByPsychologist/{dniPsychologist}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Patient> findAllPatientsByPsychologist(@PathParam("dniPsychologist") String dniPsychologist) {
        List<Patient> patients = null;
        try {
            patients = patientEJB.findAllPatientsByPsychologist(dniPsychologist);
        } catch (PatientException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return patients;
    }

}