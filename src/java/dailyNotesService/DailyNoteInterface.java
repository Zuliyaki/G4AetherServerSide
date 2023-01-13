/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyNotesService;

import entities.DailyNote;
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

    public List<DailyNote> findAllNotesByPatientId(String idPatient) throws DailyNoteNotFoundException;

    public DailyNote findPatientNoteByDate(String idPatient, Date date) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesBetweenDates(String idPatient, Date dateLow, Date dateGreat) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientEditedNotes(String idPatient) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesByNotReadable(String idPatient) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNotesBetweenDayScores(String idPatient, Double dayScoreLow, Double dayScoreGreat) throws DailyNoteNotFoundException;
    
}
