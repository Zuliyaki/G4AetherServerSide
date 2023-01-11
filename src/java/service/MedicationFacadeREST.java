/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DiagnosisService.DiagnosisInterface;
import MedicationService.MedicationInterface;
import entities.Diagnosis;
import entities.Medication;
import exceptions.DiagnosisNotFoundException;
import exceptions.MedicationNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author unaiz
 */

@Path("entities.medication")
public class MedicationFacadeREST{
  /**
     * The EJB interface
     */
    @EJB
    private MedicationInterface ejb;

    private Logger LOGGER = Logger.getLogger(DiagnosisFacadeREST.class.getName());
  

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Medication find(@PathParam("id") Long id) {
          Medication medication = null;
        try {
            LOGGER.log(Level.INFO, "getting medication by id");
            medication = ejb.findMedicationById(id);
        } catch (MedicationNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
        return medication;

    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Medication> findAll() {
        List<Medication>  medications = null;
        try {
            LOGGER.log(Level.INFO, "getting all medications");
            medications = ejb.findAllMedication();
        } catch (MedicationNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
        return medications;
    }
    
     @GET
    @Path("searchbyname/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Medication find(@PathParam("id") String name) {
          Medication medication = null;
        try {
            LOGGER.log(Level.INFO, "getting medication by id");
            medication = ejb.findMedicationByName(name);
        } catch (MedicationNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
        return medication;

    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Medication> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
       // return super.findRange(new int[]{from, to});
       return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return null;
        // return String.valueOf(super.count());
    }

    
}