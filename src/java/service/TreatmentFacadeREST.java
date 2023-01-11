/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import TreatmentService.TreatmentInterface;
import entities.EnumDay;
import entities.EnumDayTime;

import entities.Treatment;
import entities.TreatmentId;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.TreatmentNotFoundException;
import exceptions.UpdateException;
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
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author unaiz
 */

@Path("entities.treatment")
public class TreatmentFacadeREST {

    /**
     * The EJB interface
     */
    @EJB
    private TreatmentInterface ejb;

    private Logger LOGGER = Logger.getLogger(DiagnosisFacadeREST.class.getName());
/**
 * 
     * @param entity
 * 
 * @return 
 */
    /*
    private TreatmentId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;diagnosisId=diagnosisIdValue;medicationId=medicationIdValue;day=dayValue;dayTime=dayTimeValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        /*
        
        
        entities.TreatmentId key = new entities.TreatmentId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> diagnosisId = map.get("diagnosisId");
        if (diagnosisId != null && !diagnosisId.isEmpty()) {
            key.setDiagnosisId(new java.lang.Long(diagnosisId.get(0)));
        }
        java.util.List<String> medicationId = map.get("medicationId");
        if (medicationId != null && !medicationId.isEmpty()) {
            key.setMedicationId(new java.lang.Long(medicationId.get(0)));
        }
        java.util.List<String> day = map.get("day");
        if (day != null && !day.isEmpty()) {
            key.setDay(entities.EnumDay.valueOf(day.get(0)));
        }
        java.util.List<String> dayTime = map.get("dayTime");
        if (dayTime != null && !dayTime.isEmpty()) {
            key.setDayTime(entities.EnumDayTime.valueOf(dayTime.get(0)));
        }
        return key;
          
    }
*/
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createTreatment(Treatment entity) {
        try {
            LOGGER.log(Level.INFO, "creating treatment");
            ejb.createTreatment(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateTreatment(Treatment entity) {
        try {
            LOGGER.log(Level.INFO, "upadting treatment");
            ejb.updateTreatment(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
           throw new InternalServerErrorException(ex.getMessage());        
        }
    }
/**
 * 
 * @param treatmentId 
     * @param MedicationId 
     * @param Day 
     * @param Daytime 
 */
    @DELETE
    @Path("Delete/{treatmentId}/{MedicationId}/{Day}/{Daytime}")
    public void deleteTreatment(@PathParam("id") Long treatmentId,@PathParam("MedicationId")Long MedicationId,@PathParam("Day") String Day,@PathParam("Daytime") String Daytime) {
        Treatment treatment;
        TreatmentId treatmentid = null;
   
       
        
        treatmentid.setDay(EnumDay.valueOf(Day.toUpperCase().trim()));
        treatmentid.setDayTime(EnumDayTime.valueOf(Daytime.toUpperCase().trim()));
        treatmentid.setDiagnosisId(treatmentId);
        treatmentid.setMedicationId(MedicationId);
        try {
            LOGGER.log(Level.INFO, "deleting treatment");
            treatment = ejb.findTreatmentByID(treatmentid);
             ejb.deleteTreatment(treatment);
        } catch (TreatmentNotFoundException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
    }
     @GET
    @Path("get/{treatmentId}/{MedicationId}/{Day}/{Daytime}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Treatment findTreatmentByID(@PathParam("id") Long treatmentId,@PathParam("MedicationId")Long MedicationId,@PathParam("Day") EnumDay Day,@PathParam("Daytime") EnumDayTime Daytime) {
         Treatment treatment = new Treatment();
          TreatmentId treatmentid = null;
           treatmentid.setDay(Day);
        treatmentid.setDayTime(Daytime);
        treatmentid.setDiagnosisId(treatmentId);
        treatmentid.setMedicationId(MedicationId);
        try {
            LOGGER.log(Level.INFO,"get Treatment by ID");
            treatment = ejb.findTreatmentByID(treatmentid);
        } catch (TreatmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());        
        }
        return treatment;
    }

    @GET
    @Path("diagnosis/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Treatment> findTreatmentsByDiagnosisId(@PathParam("id") Long id) {
        List<Treatment> treatmentents = null;

        try {
            LOGGER.log(Level.INFO, "getting treatments by ID");
            treatmentents = ejb.findTreatmentsByDiagnosisId(id);
        } catch (TreatmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
        return treatmentents;
    }
}