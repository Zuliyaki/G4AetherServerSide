/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userService;

import entities.User;
import java.util.List;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.UserException;

/**
 *
 * @author unaibAndLeire
 */
public interface UserInterface {

    public void createUser(User user) throws CreateException;

    public void updateUser(User user, String dniUser) throws UpdateException;

    public void deleteUser(String dniUser) throws DeleteException;

    public User logInUser(String dniUser, String passwordUser) throws UserException;

    public List<User> findAllUsers() throws UserException;

    public User findUserByDni(String dniUser) throws UserException;

}
