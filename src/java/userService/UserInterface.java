/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userService;

import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.UserException;
import java.util.List;

/**
 *
 * @author Leire
 */
public interface UserInterface {

    public void createUser(User user) throws CreateException;

    public void updateUser(User user) throws UpdateException;

    public void deleteUser(String dni) throws DeleteException;

    public List<User> findAllUsers() throws UserException;
    
    public List<User> findAllPatients() throws UserException;
    
    public List<User> findAllPsychologists() throws UserException;
    
    public List<User> findAllPatientsByPsychologist(String dniPsychologist) throws UserException;
    
    public User findUserByDni(String dni) throws UserException;

}
