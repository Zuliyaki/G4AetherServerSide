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
public class EJBDailyNoteManager implements DailyNoteInterface {

    @Override
    public void createDailyNote(DailyNote dailyNote) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDailyNote(DailyNote dailyNote) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDailyNote(DailyNote dailyNote) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findAllDailyNotes() throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findDailyNoteById(Long idDailyNote) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findAllNotesByPatient(Patient patient) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findPatientNoteByDate(DailyNote dailyNote) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findPatientEditedNotes(DailyNote dailyNote) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DailyNote> findPatientNoteByNotReadable(DailyNote dailyNote) throws DailyNoteNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
