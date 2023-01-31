/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Psychologist;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PsychologistException;
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
import psychologistService.PsychologistInterface;

/**
 *
 * @author unaibAndLeire
 */
@Path("entities.psychologist")
public class PsychologistFacadeREST {

    @EJB
    private PsychologistInterface psychologistEJB;

    private static final Logger LOGGER = Logger.getLogger(PsychologistFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createPsychologist(Psychologist entity) {
        try {
            psychologistEJB.createPsychologist(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editPsychologist(Psychologist entity) {
        try {
            psychologistEJB.updatePsychologist(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{dni}")
    public void removePsychologist(@PathParam("dni") String dni) {
        try {
            psychologistEJB.deletePsychologist(dni);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Path("sendRecoveryEmail")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void sendRecoveryEmail(Psychologist entity) {
        try {
            psychologistEJB.sendRecoveryEmail(entity);
        } catch (PsychologistException ex) {
            Logger.getLogger(PsychologistFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Psychologist> findAllPsychologists() {
        List<Psychologist> users = null;
        try {
            users = psychologistEJB.findAllPsychologists();
        } catch (PsychologistException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return users;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Psychologist> findPsychologistsByEmail(String emailIntro) {
        List<Psychologist> users = null;
        try {
            users = psychologistEJB.findPsychologistsByEmail(emailIntro);
        } catch (PsychologistException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return users;
    }
}
