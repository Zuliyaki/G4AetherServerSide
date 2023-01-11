/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import entities.DailyNote;
import entities.Patient;
import dailyNotesService.DailyNoteInterface;
import exceptions.CreateException;
import exceptions.DailyNoteNotFoundException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author unaib
 */
@Path("entities.dailynote")
public class DailyNoteFacadeREST {

    @EJB
    private DailyNoteInterface dailyNoteEJB;

    private static final Logger LOGGER = Logger.getLogger(DailyNoteFacadeREST.class.getName());

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
            dailyNotes = dailyNoteEJB.findAllNotes();
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("findByPatient/{patientId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findAllDailyNotesByPatientId(@PathParam("patientId") String idPatient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findAllNotesByPatientId(idPatient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("findByDate/{patientId}/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DailyNote findPatientDailyNoteByDate(@PathParam("patientId") String idPatient, @PathParam("date") String dateIntro) {
        DailyNote dailyNote = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(dateIntro);
            dailyNote = dailyNoteEJB.findPatientNoteByDate(idPatient, date);
        } catch (ParseException ex) {
            Logger.getLogger(DailyNoteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNote;
    }

    @GET
    @Path("findBetweenDates/{patientId}/{dateLow}/{dateGreat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientDailyNotesBetweenDates(@PathParam("patientId") String idPatient, @PathParam("dateLow") String dateIntroLow, @PathParam("dateGreat") String dateIntroGreat) {
        List<DailyNote> dailyNotes = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date dateLow = format.parse(dateIntroLow);
            Date dateGreat = format.parse(dateIntroGreat);
            dailyNotes = dailyNoteEJB.findPatientNotesBetweenDates(idPatient, dateLow, dateGreat);
        } catch (ParseException ex) {
            Logger.getLogger(DailyNoteFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("findEditedNotes/{patientId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientEditedDailyNotes(@PathParam("patientId") String idPatient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientEditedNotes(idPatient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("findNotReadableNotes/{patientId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientDailyNotesByNotReadable(@PathParam("patientId") String idPatient) {
        List<DailyNote> dailyNotes = null;
        try {
            dailyNotes = dailyNoteEJB.findPatientNotesByNotReadable(idPatient);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }

    @GET
    @Path("findBetweenScores/{patientId}/{dayScoreLow}/{dayScoreGreat}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DailyNote> findPatientNotesBetweenDayScores(@PathParam("patientId") String idPatient, @PathParam("dayScoreLow") Double dayScoreLow, @PathParam("dayScoreGreat") Double dayScoreGreat) {
        List<DailyNote> dailyNotes = null;
        try {
            //Double dayScoreLow = Double.parseDouble(dayScoreLowIntro);
            //Double dayScoreGreat = Double.parseDouble(dayScoreGreatIntro);
            dailyNotes = dailyNoteEJB.findPatientNotesBetweenDayScores(idPatient, dayScoreLow, dayScoreGreat);
        } catch (DailyNoteNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return dailyNotes;
    }
}
