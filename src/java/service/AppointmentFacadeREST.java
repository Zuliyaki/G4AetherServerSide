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
 * @author unaib
 */
@Stateless
@Path("entities.appointment")
public class AppointmentFacadeREST {

    @EJB
    private AppointmentInterface appointmentEJB;

    private static final Logger LOGGER = Logger.getLogger(AppointmentFacadeREST.class.getName());

    /**
     * 
     * @param entity 
     */
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

    /**
     * 
     * @param idAppointment
     * @param entity 
     */
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

    /**
     * 
     * @param idAppointment 
     */
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

    /**
     * 
     * @param idAppointment
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appointment findAppointmentById(@PathParam("id") Long idAppointment) {
        Appointment appointment = null;
        try {
            appointment = appointmentEJB.findAppointmentById(idAppointment);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointment;

    }

    /**
     * 
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appointment> findAllAppointments() {
        List<Appointment> appointments = null;
        try {
            appointments = appointmentEJB.findAllAppointments();
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointments;
    }
    
    /**
     * 
     * @param date
     * @return 
    
    @GET
    @Path("{findByDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Appointment findAppointmentByDate(@PathParam("date") Date date) {
        Appointment appointment = null;
        try {
            appointment = appointmentEJB.findAppointmentByDate(date);
        } catch (AppointmentNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return appointment;    
    }
    *  */
}
