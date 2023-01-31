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
import exceptions.UserException;
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

    
    @Override
    public void createUser(User user) throws CreateException {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    @Override
     public void updateUser(User user, String dniUser) throws UpdateException {
        try {
            if (!entityManager.contains(user)) {
                entityManager.merge(user);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(String dni) throws DeleteException {
        try {
            entityManager.remove(entityManager.merge(findUserByDni(dni)));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }
    
    /**
     * Check if a user exists
     *
     * @param dniUser User dni used for log in
     * @param passwordUser User password used for log in
     * @return The user if exists
     * @throws exceptions.UserException
     */
    @Override
    public User logInUser(String dniUser, String passwordUser) throws UserException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("singIn").setParameter("dniUser", dniUser).setParameter("passwordUser", passwordUser).getSingleResult();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws UserException {
        List<User> users = null;
        try {
            users = entityManager.createNamedQuery("findAllUsers").getResultList();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return users;
    }

    @Override
    public User findUserByDni(String dniUser) throws UserException {
        User user = null;
        try {
            user = (User) entityManager.createNamedQuery("findUserByDni").setParameter("dniUser", dniUser).getSingleResult();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return user;
    }

}
