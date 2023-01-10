package appointmentService;

import java.util.List;
import entities.Appointment;
import exceptions.AppointmentNotFoundException;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;

/**
 *
 * @author Janam
 */
public interface AppointmentInterface {

    public Appointment createAppointment(Appointment appointment) throws CreateException;

    public Appointment findAppointmentById(Long idAppointment) throws AppointmentNotFoundException;

    public List<Appointment> findAllAppointments() throws AppointmentNotFoundException;

    public Appointment updateAppointment(Appointment appointment) throws UpdateException;

    public void deleteAppointment(Long idAppointment) throws DeleteException;
}
