/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userService;

import entities.User;
import java.util.List;
import exceptions.UserNotFoundException;

/**
 *
 * @author Leire
 */
public interface UserInterface {

    public User logInUser(String dniUser, String passwordUser) throws UserNotFoundException;

    public List<User> findAllUsers() throws UserNotFoundException;

    public User findUserByDni(String dniUser) throws UserNotFoundException;
    
}
