package appointmentService;

import entities.Appointment;
import exceptions.AppointmentNotFoundException;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.Date;
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
    public void createAppointment(Appointment appointment) throws CreateException {
        try {
            entityManager.persist(appointment);
        } catch (Exception e) {
            throw new CreateException("Error creating appointment" + e.getMessage());
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) throws UpdateException {
        try {
            if (!entityManager.contains(appointment)) {
                entityManager.merge(appointment);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException("Error updating appointment" + e.getMessage());
        }
    }

    @Override
    public void deleteAppointment(Long idAppointment) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(getAppointmentById(idAppointment)));
        } catch (AppointmentNotFoundException e) {
            throw new DeleteException("Error deleting appointment" + e.getMessage());
        }
    }

    @Override
    public Appointment getAppointmentById(Long idAppointment) throws AppointmentNotFoundException {
        Appointment appointment;
        try {
            appointment = entityManager.find(Appointment.class, idAppointment);
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding appointment" + e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() throws AppointmentNotFoundException {
        List<Appointment> appointment;
        try {
            appointment = entityManager.createNamedQuery("getAllAppointments").getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding all appointments" + e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentByPatient(String Patient) throws AppointmentNotFoundException {
        List<Appointment> appointment;
        try {
            appointment = entityManager.createNamedQuery("getAppointmentByPatient").setParameter("idUser", Patient).getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding appointments by patient" + e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentByPsychologist(String psychologist) throws AppointmentNotFoundException {
        List<Appointment> appointment;
        try {
            appointment = entityManager.createNamedQuery("getAppointmentByPsychologist").setParameter("idUser", psychologist).getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding appointments by psychologist" + e.getMessage());
        }
        return appointment;

    }

    @Override
    public Appointment getAppointmentByDate(Date appointmentDate) throws AppointmentNotFoundException {
        Appointment appointment;
        try {
            appointment = (Appointment) entityManager.createNamedQuery("getAppointmentByDate").setParameter("appointmentDate", appointmentDate).getSingleResult();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding appointments by date" + e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAppointmentByChange(Boolean appointmentChange) throws AppointmentNotFoundException {
        try {
            return entityManager.createNamedQuery("getAppointmentByChange").setParameter("appointmentChange", appointmentChange).getResultList();
        } catch (Exception e) {
            throw new AppointmentNotFoundException("Error finding appointments by change" + e.getMessage());
        }
    }
}
