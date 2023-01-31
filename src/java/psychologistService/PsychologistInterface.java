/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psychologistService;

import java.util.List;
import entities.Psychologist;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.PsychologistException;

/**
 *
 * @author unaibAndLeire
 */
public interface PsychologistInterface {

    public void createPsychologist(Psychologist psychologist) throws CreateException;

    public void updatePsychologist(Psychologist psychologist) throws UpdateException;

    public void deletePsychologist(String dni) throws DeleteException;

    public Psychologist findPsychologistById(String dni) throws PsychologistException;

    public List<Psychologist> findAllPsychologists() throws PsychologistException;  

    public List<Psychologist> findPsychologistsByEmail(String email) throws PsychologistException;    
    
    public void sendRecoveryEmail(Psychologist psychologist) throws PsychologistException;
    
    public void changePassword(Psychologist psychologist, String oldPassword, String newPassword) throws PsychologistException;

}
