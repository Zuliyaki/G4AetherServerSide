/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.MentalDisease;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.MentalDiseaseException;
import java.util.List;
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
import mentalDiseaseService.MentalDiseaseInterface;

/**
 *
 * @author 2dam
 */
@Path("entities.mentaldisease")
public class MentalDiseaseFacadeREST {

    @EJB
    private MentalDiseaseInterface mentalDiseaseEJB;

    private static final Logger LOGGER = Logger.getLogger(MentalDiseaseFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(MentalDisease entity) {
        try {
            mentalDiseaseEJB.createMentalDisease(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long idMentalDisease, MentalDisease entity) {
        try {
            mentalDiseaseEJB.updateMentalDisease(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long idMentalDisease) {
        try {
            mentalDiseaseEJB.deleteMentalDisease(idMentalDisease);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MentalDisease> getAllMentalDiseases() {
        List<MentalDisease> mentalDiseases = null;
        try {
            mentalDiseases = mentalDiseaseEJB.getAllMentalDiseases();
        } catch (MentalDiseaseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return mentalDiseases;
    }

    @GET
    @Path("getAllByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<MentalDisease> getAllMentalDiseasesOrderByName(@PathParam("name") String mdname) {
        List<MentalDisease> mentalDiseases = null;
        try {
            mentalDiseases = mentalDiseaseEJB.getAllMentalDiseasesOrderByName(mdname);
        } catch (MentalDiseaseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return mentalDiseases;
    }

    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MentalDisease getMentalDiseasesByName(@PathParam("name") String mdname) {
        MentalDisease mentalDisease = null;
        try {
            mentalDisease = mentalDiseaseEJB.getMentalDiseasesByName(mdname);
        } catch (MentalDiseaseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return mentalDisease;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public MentalDisease getMentalDiseasesById(@PathParam("id") Long idMentalDisease) {
        MentalDisease mentalDisease = null;
        try {
            mentalDisease = mentalDiseaseEJB.getMentalDiseasesById(idMentalDisease);
        } catch (MentalDiseaseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return mentalDisease;
    }

}
