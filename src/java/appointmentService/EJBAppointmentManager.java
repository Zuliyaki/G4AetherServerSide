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

    @Override
    public Appointment createAppointment(Appointment appointment) throws CreateException {
        try {
            entityManager.persist(appointment);
            entityManager.flush();
            return appointment;
        } catch (Exception e) {
            throw new CreateException("Error creating appointment: " + e.getMessage());
        }
    }

    @Override
    public Appointment findAppointmentById(Long idAppointment) throws AppointmentNotFoundException {
        try {
            Appointment appointment = entityManager.find(Appointment.class, idAppointment);
            if (appointment == null) {
                throw new AppointmentNotFoundException("Appointment with id " + idAppointment + " not found");
            }
            return appointment;
        } catch (AppointmentNotFoundException e) {
            throw new AppointmentNotFoundException("Error retrieving appointment: " + e.getMessage());
        }

    }

    @Override
    public List<Appointment> findAllAppointments() throws AppointmentNotFoundException {
        try {
            List<Appointment> appointments = entityManager.createQuery("SELECT a FROM Appointment a").getResultList();
            if (appointments == null) {
                throw new AppointmentNotFoundException("No appointments found");
            }
            return appointments;
        } catch (AppointmentNotFoundException e) {
            throw new AppointmentNotFoundException("Error retrieving appointments: " + e.getMessage());
        }

    }

    @Override
    public Appointment updateAppointment(Appointment appointment) throws UpdateException {
        try {
            entityManager.merge(appointment);
            entityManager.flush();
            return appointment;
        } catch (Exception e) {
            throw new UpdateException("Error updating appointment: " + e.getMessage());
        }

    }

    @Override
    public void deleteAppointment(Long idAppointment) throws DeleteException {
        try {
            Appointment appointment = entityManager.find(Appointment.class, idAppointment);
            if (appointment == null) {
                throw new DeleteException("Appointment with id " + idAppointment + " not found");
            }
            entityManager.remove(appointment);
            entityManager.flush();
        } catch (DeleteException e) {
            throw new DeleteException("Error deleting appointment: " + e.getMessage());
        }
    }
}
