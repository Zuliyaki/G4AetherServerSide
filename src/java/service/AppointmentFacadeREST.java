package service;

import appointmentService.AppointmentInterface;
import entities.Appointment;
import exceptions.AppointmentNotFoundException;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
 * @author Janam
 */
@Stateless
@Path("entities.appointment")
public class AppointmentFacadeREST {

    @EJB
    private AppointmentInterface appointmentEJB;

    private static final Logger LOGGER = Logger.getLogger(AppointmentFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Appointment entity) {
        try {
            appointmentEJB.createAppointment(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long idAppointment, Appointment entity) {
        try {
            appointmentEJB.updateAppointment(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long idAppointment) {
        try {
            appointmentEJB.deleteAppointment(idAppointment);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appointment getAppointmentById(@PathParam("id") Long idAppointment) {
        Appointment appointment = null;
        try {
            appointment = appointmentEJB.getAppointmentById(idAppointment);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointment;
    }

   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.getAllAppointments();
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }

    @GET
    @Path("getByPatient/{patient_dni}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> getAppointmentByPatient(@PathParam("patient_dni") String idPatient) {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.getAppointmentByPatient(idPatient);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }

    @GET
    @Path("getByPsychologist/{psychologist_dni}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> getAppointmentByPsychologist(@PathParam("patient_dni") String idPsychologist) {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.getAppointmentByPsychologist(idPsychologist);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }

    @GET
    @Path("getAppointmentByDate/{appointmentdate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> getAppointmentByDate(@PathParam("appointmentdate") Date appointmentdate) {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.getAppointmentByDate(appointmentdate);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }

    @GET
    @Path("getAppointmentByChange/{appointmentchange}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> getAppointmentByChange(@PathParam("appointmentchange") Boolean appointmentchange) {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.getAppointmentByChange(appointmentchange);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }
}
