package entities;

import java.util.List;

import javax.persistence.PersistenceContext;

@PersistenceContext
public interface EntityDailyNotesManager {

    /**
     *
     * @param patient
     */
    public void patientAddNote(Patient patient);

    /**
     *
     * @param patient
     */
    public void patientEditNote(Patient patient);

    /**
     *
     * @param patient
     */
    public void patientRemoveNote(Patient patient);

    /**
     *
     * @param patient
     * @return
     */
    public List<DailyNote> patientSelectAllNotes(Patient patient);

}
