/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import diagnosisService.DiagnosisInterface;
import entities.Diagnosis;
import entities.Patient;
import exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.InternalServerErrorException;

/**
 *
 * @author unaiz
 */
@Path("entities.diagnosis")
public class DiagnosisFacadeREST {

    /**
     * The EJB interface
     */
    @EJB
    private DiagnosisInterface ejb;

    private Logger LOGGER = Logger.getLogger(DiagnosisFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createDiagnosis(Diagnosis entity) {
        try {
            LOGGER.log(Level.INFO, "Creating a Diagnosis");
            ejb.createDiagnosis(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateDiagnosis(Diagnosis entity) {
        try {
            LOGGER.log(Level.INFO, "updating diagnosis");
            ejb.updateDiagnosis(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteDiagnosis(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Deleting Diagnosis {0}", id);
            ejb.deleteDiagnosis(ejb.findDiagnosisById(id));
        } catch (DiagnosisNotFoundException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Diagnosis findDiagnosisById(@PathParam("id") Long id) {
        Diagnosis diagnosis = null;
        try {
            LOGGER.log(Level.INFO, "getting diagnosis by id");
            diagnosis = ejb.findDiagnosisById(id);
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return diagnosis;
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findAllDiagnosis() {
        List<Diagnosis> diagnosises = null;
        try {
            LOGGER.log(Level.INFO, "getting all diagnosis");
            diagnosises = ejb.findAllDiagnosis();
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return diagnosises;
    }

    @GET
    @Path("patients/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findAllDiagnosisByPatient(@PathParam("id") String id) {
        List<Diagnosis> diagnosises = null;

        try {
            LOGGER.log(Level.INFO, "getting diagnosis by patient id");
            diagnosises = ejb.findAllDiagnosisByPatient(id);
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return diagnosises;
    }

    @GET
    @Path("onteraphy/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findAllIfPatientOnTeraphy(@PathParam("id") String id) {
        List<Diagnosis> diagnosises = null;
        try {
            LOGGER.log(Level.INFO, "getting diagnosis by patient id");
            diagnosises = ejb.findAllIfPatientOnTeraphy(id);
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return diagnosises;
    }

    @GET
    @Path("findDiagnosisByPatientIdbeetweenDates/{id}/{dateLow}/{dateGreat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findPatientDiagnosisByDate(@PathParam("id") String idPatient, @PathParam("dateLow") String dateIntroLow, @PathParam("dateGreat") String dateIntroGreat) {
        List<Diagnosis> diagnosises = null;
        Patient patient = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            LOGGER.log(Level.INFO, "getting diagnosis by patient id");
            Date dateLow = format.parse(dateIntroLow);
            Date dateGreat = format.parse(dateIntroGreat);

            diagnosises = ejb.findPatientDiagnosisByDate(idPatient, dateLow, dateGreat);
        } catch (ParseException | DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }
        return diagnosises;
    }

}
