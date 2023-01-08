/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.InternalServerErrorException;
import DiagnosisService.DiagnosisInterface;
import entities.Diagnosis;
import exceptions.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Stateless
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
    public void create(Diagnosis entity) {
        try {
            LOGGER.log(Level.INFO, "Creating a Diagnosis");
            ejb.createDiagnosis(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Diagnosis entity) {
        try {
            LOGGER.log(Level.INFO, "updating diagnosis");
            ejb.updateDiagnosis(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Deleting Diagnosis {0}", id);
            ejb.deleteDiagnosis(ejb.findDiagnosisById(id));
        } catch (DiagnosisNotFoundException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Diagnosis find(@PathParam("id") Long id) {
        Diagnosis diagnosis = null;
        try {
            LOGGER.log(Level.INFO, "getting diagnosis by id");
            diagnosis = ejb.findDiagnosisById(id);
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
        return diagnosis;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findAll() {
         List<Diagnosis> diagnosises = null;
        try {
            LOGGER.log(Level.INFO, "getting diagnosis by id");
            diagnosises = ejb.findAllDiagnosis();
        } catch (DiagnosisNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            // throw new InternalServerErrorException(ex.getMessage());        
        }
        return diagnosises;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Diagnosis> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        //return super.findRange(new int[]{from, to});
        return null;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
       // return String.valueOf(super.count());
       return null;
    }

}
