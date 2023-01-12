package appointmentService;

import java.util.List;
import entities.Appointment;
import entities.Patient;
import entities.Psychologist;
import exceptions.AppointmentNotFoundException;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.Date;

/**
 *
 * @author Janam
 */
public interface AppointmentInterface {

    public void createAppointment(Appointment appointment) throws CreateException;

    public void updateAppointment(Appointment appointment) throws UpdateException;

    public void deleteAppointment(Long idAppointment) throws DeleteException;

    public Appointment getAppointmentById(Long idAppointment) throws AppointmentNotFoundException;

    public List<Appointment> getAllAppointments() throws AppointmentNotFoundException;

    public List<Appointment> getAppointmentByPatient(String idPatient) throws AppointmentNotFoundException;

    public List<Appointment> getAppointmentByPsychologist(String idPsychologist) throws AppointmentNotFoundException;

    public Appointment getAppointmentByDate(Date appointmentDate) throws AppointmentNotFoundException;

    public List<Appointment> getAppointmentByChange(Boolean appointmentChange) throws AppointmentNotFoundException;

}
