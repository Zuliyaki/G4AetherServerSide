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

/**
 *
 * @author unaib
 */
public interface DailyNoteInterface {

    public void createDailyNote(DailyNote dailyNote) throws CreateException;

    public void updateDailyNote(DailyNote dailyNote) throws UpdateException;

    public void deleteDailyNote(Long idDailyNote) throws DeleteException;

    public DailyNote findDailyNoteById(Long idDailyNote) throws DailyNoteNotFoundException;

    public List<DailyNote> findAllNotes() throws DailyNoteNotFoundException;

    public List<DailyNote> findAllNotesByPatient(Patient patient) throws DailyNoteNotFoundException;

    public DailyNote findPatientNoteByDate(Patient patient, Date date) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesBetweenDates(Patient patient, Date dateLow, Date dateGreat) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientEditedNotes(Patient patient) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesByNotReadable(Patient patient) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesByDayScores(Patient patient, Long dayScoreLow, Long dayScoreGreat) throws DailyNoteNotFoundException;

}
