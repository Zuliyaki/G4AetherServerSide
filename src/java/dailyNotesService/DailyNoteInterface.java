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
import java.util.List;

/**
 *
 * @author unaib
 */
public interface DailyNoteInterface {

    public void createDailyNote(DailyNote dailyNote) throws CreateException;

    public void updateDailyNote(DailyNote dailyNote) throws UpdateException;

    public void deleteDailyNote(DailyNote dailyNote) throws DeleteException;

    public List<DailyNote> findAllDailyNotes() throws DailyNoteNotFoundException;

    public List<DailyNote> findDailyNoteById(Long idDailyNote) throws DailyNoteNotFoundException;

    public List<DailyNote> findAllNotesByPatient(Patient patient) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNoteByDate(DailyNote dailyNote) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientEditedNotes(DailyNote dailyNote) throws DailyNoteNotFoundException;

    public List<DailyNote> findPatientNoteByNotReadable(DailyNote dailyNote) throws DailyNoteNotFoundException;

}
