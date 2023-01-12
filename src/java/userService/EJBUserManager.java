/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userService;

import entities.User;
import exceptions.UserNotFoundException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unaibAndLeire
 */
@Stateless
public class EJBUserManager implements UserInterface {

    @PersistenceContext(unitName = "G4AetherPU")
    private EntityManager entityManager;

    /**
     * Check if a user exists
     *
     * @param dniUser User dni used for log in
     * @param passwordUser User password used for log in
     * @return The user if exists
     * @throws exceptions.UserNotFoundException
     */
    @Override
    public User logInUser(String dniUser, String passwordUser) throws UserNotFoundException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("singIn").setParameter("dniUser", dniUser).setParameter("passwordUser", passwordUser).getSingleResult();
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws UserNotFoundException {
        List<User> users = null;
        try {
            users = entityManager.createNamedQuery("findAllUsers").getResultList();
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
        return users;
    }

    @Override
    public User findUserByDni(String dniUser) throws UserNotFoundException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("singIn").setParameter("dniUser", dniUser).getSingleResult();
        } catch (Exception e) {
            throw new UserNotFoundException(e.getMessage());
        }
        return user;
    }

}
