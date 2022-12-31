/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyNotesService;

import entities.DailyNote;
import entities.Patient;
import exceptions.CreateException;
import exceptions.DailyNoteNotFoundException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author unaib
 */
@Stateless
public class EJBDailyNoteManager implements DailyNoteInterface {

    private EntityManager entityManager;

    /**
     * Create a new daily note
     *
     * @param dailyNote Daily note to save
     * @throws CreateException
     */
    @Override
    public void createDailyNote(DailyNote dailyNote) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Update a daily note
     *
     * @param dailyNote Daily note to update
     * @throws UpdateException
     */
    @Override
    public void updateDailyNote(DailyNote dailyNote) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Delete a daily note by id
     *
     * @param idDailyNote Id of the daily note to delete
     * @throws DeleteException
     */
    @Override
    public void deleteDailyNote(Long idDailyNote) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Find all daily notes
     *
     * @return List of all daily notes
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findAllDailyNotes() throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Find all daily notes of a patient
     *
     * @param patient Patient to search all daily notes from
     * @return List of all daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findAllDailyNotesByPatient(Patient patient) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public DailyNote findPatientDailyNoteByDate(Patient patient, Date date) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<DailyNote> findPatientDailyNotesBetweenDates(Patient patient, Date dateLow, Date dateGreat) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Find all modify daily notes of a patient
     *
     * @param patient Patient to search the daily notes from
     * @return List of all modify daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientEditedDailyNotes(Patient patient) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Find all not readable daily notes of a patient
     *
     * @param patient Patient to search the daily notes from
     * @return List of all not readable daily notes of a patient
     * @throws DailyNoteNotFoundException
     */
    @Override
    public List<DailyNote> findPatientDailyNotesByNotReadable(Patient patient) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<DailyNote> findPatientDailyNotesByDayScores(Patient patient, Long dayScoreLow, Long dayScoreGreat) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
