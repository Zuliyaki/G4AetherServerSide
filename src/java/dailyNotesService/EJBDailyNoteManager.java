/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyNotesService;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.DailyNote;
import entities.Patient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.DailyNoteNotFoundException;

/**
 *
 * @author unaib
 */
@Stateless
public class EJBDailyNoteManager implements DailyNoteInterface {

    @PersistenceContext(unitName = "AetherPU")
    private EntityManager entityManager;

    /**
     * Create a new daily note
     *
     * @param dailyNote Daily note to save
     * @throws CreateException
     */
    @Override
    public void createDailyNote(DailyNote dailyNote) throws CreateException {
        try {
            entityManager.persist(dailyNote);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Update a daily note
     *
     * @param dailyNote Daily note to update
     * @throws UpdateException
     */
    @Override
    public void updateDailyNote(DailyNote dailyNote) throws UpdateException {
        try {
            if (!entityManager.contains(dailyNote)) {
                entityManager.merge(dailyNote);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Delete a daily note by id
     *
     * @param idDailyNote Id of the daily note to delete
     * @throws DeleteException
     */
    @Override
    public void deleteDailyNote(Long idDailyNote) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(findDailyNoteById(idDailyNote)));
        } catch (DailyNoteNotFoundException e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Find a daily note by id
     *
     * @param idDailyNote Id of the daily note to search for
     * @return Selected daily note
     * @throws DailyNoteNotFoundException
     */
    @Override
    public DailyNote findDailyNoteById(Long idDailyNote) throws DailyNoteNotFoundException {
        DailyNote dailyNote;
        try {
            dailyNote = (DailyNote) entityManager.createNamedQuery("findDailyNoteById").setParameter("idDailyNote", idDailyNote).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNote;
    }

    /**
     * Find all daily notes
     *
     * @return List of all daily notes
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findAllNotes() throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findAllNotes").getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

    /**
     * Find all daily notes of a patient
     *
     * @param patient Patient to search all daily notes from
     * @return List of all daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findAllNotesByPatient(Patient patient) throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findAllNotesByPatient").setParameter("idUser", patient.getDni()).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

    /**
     * Find all daily notes of a patient from a of a specific date
     *
     * @param patient Patient to search all daily notes from
     * @param date Date of the daily note
     * @return List of the daily note of a patient in the specific date
     * @throws DailyNoteNotFoundException
     */
    @Override
    public DailyNote findPatientNoteByDate(Patient patient, Date date) throws DailyNoteNotFoundException {
        DailyNote dailyNote;
        try {
            dailyNote = (DailyNote) entityManager.createNamedQuery("findPatientNoteByDate").setParameter("idUser", patient.getDni()).setParameter("noteDate", date).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNote;
    }

    /**
     * Find all daily notes of a patient between two dates
     *
     * @param patient Patient to search the daily notes from
     * @param dateLow Date from which to search
     * @param dateGreat Date to search for
     * @return List of all daily notes of a patient between two dates
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientNotesBetweenDates(Patient patient, Date dateLow, Date dateGreat) throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findPatientNotesBetweenDates").setParameter("idUser", patient.getDni()).setParameter("noteDateLow", dateLow).setParameter("noteDateGreat", dateGreat).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

    /**
     * Find all modify daily notes of a patient
     *
     * @param patient Patient to search the daily notes from
     * @return List of all modify daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientEditedNotes(Patient patient) throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findPatientEditedNotes").setParameter("idUser", patient.getDni()).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

    /**
     * Find all not readable daily notes of a patient
     *
     * @param patient Patient to search the daily notes from
     * @return List of all not readable daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientNotesByNotReadable(Patient patient) throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findPatientEditedNotes").setParameter("idUser", patient.getDni()).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

    /**
     * Find all daily notes of a patient between two scores
     *
     * @param patient Patient to search the daily notes from
     * @param dayScoreLow Score from which to search
     * @param dayScoreGreat Score to look for
     * @return List of all daily notes of a patient between to scores
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientNotesByDayScores(Patient patient, Long dayScoreLow, Long dayScoreGreat) throws DailyNoteNotFoundException {
        List<DailyNote> dailyNotes;
        try {
            dailyNotes = entityManager.createNamedQuery("findPatientNotesBetweenDates").setParameter("idUser", patient.getDni()).setParameter("dayScoreLow", dayScoreLow).setParameter("dayScoreGreat", dayScoreGreat).getResultList();
        } catch (Exception e) {
            throw new DailyNoteNotFoundException(e.getMessage());
        }
        return dailyNotes;
    }

}
