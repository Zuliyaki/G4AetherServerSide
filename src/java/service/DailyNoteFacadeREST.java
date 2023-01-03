/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dailyNotesService.DailyNoteInterface;
import entities.DailyNote;
import entities.Patient;
import exceptions.CreateException;
import exceptions.DailyNoteNotFoundException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.Date;
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
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author unaib
 */
@Path("entities.dailynote")
public class DailyNoteFacadeREST {

    @EJB
    private DailyNoteInterface dailyNoteEJB;

    private Logger LOGGER = Logger.getLogger(DailyNoteFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(DailyNote entity) {
        try {
            dailyNoteEJB.createDailyNote(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, DailyNote entity) {
        try {
            dailyNoteEJB.updateDailyNote(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            dailyNoteEJB.deleteDailyNote(id);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DailyNote findDailyNoteById(@PathParam("id") Long id) {
        DailyNote dailyNote = null;
        try {
            dailyNote = dailyNoteEJB.findDailyNoteById(id);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNote;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findAllDailyNotes() {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findAllDailyNotes();
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findAllDailyNotesByPatient(@PathParam("patient") Patient patient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findAllDailyNotesByPatient(patient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DailyNote findPatientDailyNoteByDate(@PathParam("patient") Patient patient, @PathParam("date") Date date) {
        DailyNote dailyNote = null;
        try {
            dailyNote = dailyNoteEJB.findPatientDailyNoteByDate(patient, date);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNote;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientDailyNotesBetweenDates(@PathParam("patient") Patient patient, @PathParam("dateLow") Date dateLow, @PathParam("dateGreat") Date dateGreat) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientDailyNotesBetweenDates(patient, dateLow, dateGreat);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientEditedDailyNotes(@PathParam("patient") Patient patient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientEditedDailyNotes(patient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientDailyNotesByNotReadable(@PathParam("patient") Patient patient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientDailyNotesByNotReadable(patient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientDailyNotesByDayScores(@PathParam("patient") Patient patient, @PathParam("dayScoreLow") Long dayScoreLow, @PathParam("dayScoreGreat") Long dayScoreGreat) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientDailyNotesByDayScores(patient, dayScoreLow, dayScoreGreat);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }
}
