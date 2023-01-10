package appointmentService;

import entities.Appointment;
import exceptions.AppointmentNotFoundException;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Janam
 */
@Stateless
public class EJBAppointmentManager implements AppointmentInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;

    /**
     * create new an appointment
     *
     * @param appointment to save
     * @throws CreateException
     */
    @Override
    public void createAppointment(Appointment appointment) throws CreateException {
        try {
            entityManager.persist(appointment);
        } catch (Exception e) {
            throw new CreateException("Error creating appointment: " + e.getMessage());
        }
    }

    /**
     * update an appointment
     *
     * @param appointment to update
     * @throws UpdateException
     */
    @Override
    public void updateAppointment(Appointment appointment) throws UpdateException {

        try {
            if (!entityManager.contains(appointment)) {
                entityManager.merge(appointment);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException("Error updating appointment: " + e.getMessage());
        }
    }

    /**
     * Delete an appointment by idAppointment
     *
     * @param idAppointment to delete
     * @throws DeleteException
     */
    @Override
    public void deleteAppointment(Long idAppointment) throws DeleteException {

        try {
            entityManager.remove(entityManager.merge(findAppointmentById(idAppointment)));
        } catch (AppointmentNotFoundException e) {
            throw new DeleteException("Error deleting appointment: " + e.getMessage());
        }
    }

    /**
     * Find a appointment by idAppointment
     *
     * @param idAppointment Id of the appointment to search for
     * @return Selected appointment
     * @throws AppointmentNotFoundException
     */
    @Override
    public Appointment findAppointmentById(Long idAppointment) throws AppointmentNotFoundException {

        Appointment appointment;
        try {
            appointment = (Appointment) entityManager.createNamedQuery("findDailyNoteById").setParameter("idAppointment", idAppointment).getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error retrieving appointment: " + e.getMessage());
        }
        return appointment;
    }

    /**
     * find all appointments
     *
     * @return list of all appointments
     * @throws AppointmentNotFoundException
     */
    @Override
    public List<Appointment> findAllAppointments() throws AppointmentNotFoundException {

        List<Appointment> appointments;
        try {
            appointments = entityManager.createNamedQuery("findAllAppointments").getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }
}
