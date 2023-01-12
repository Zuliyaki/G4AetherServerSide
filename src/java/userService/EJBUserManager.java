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
     * Create a new user
     *
     * @param user User to save
     * @throws CreateException
     */
    @Override
    public void createUser(User user) throws CreateException {
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Update a user
     *
     * @@param user User to update
     * @throws UpdateException
     */
    @Override
    public void updateUser(User user) throws UpdateException {
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
        } catch (UserException e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Get all users
     *
     * @return List of all users
     * @throws UserException
     */
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

    /**
     * Get an user by dni
     *
     * @param dni Dni of the user to search for
     * @return Selected user
     * @throws UserException
     */
    @Override
    public User findUserByDni(String dni) throws UserException {
        User user;
        try {
            user = entityManager.find(User.class, dni);
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return user;
    }

    /**
     * Get all patients
     *
     * @return List of all patients
     * @throws UserException
     */
    @Override
    public List<User> findAllPatients() throws UserException {
        List<User> users;
        try {
            users = entityManager.createNamedQuery("findAllPatients").getResultList();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return users;
    }

    /**
     * Get all psychologists
     *
     * @return List of all psychologists
     * @throws UserException
     */
    @Override
    public List<User> findAllPsychologists() throws UserException {
        List<User> users;
        try {
            users = entityManager.createNamedQuery("findAllPsychologists").getResultList();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return users;
    }

    @Override
    public List<User> findAllPatientsByPsychologist(String dniPsychologist) throws UserException {
        List<User> users;
        try {
            users = entityManager.createNamedQuery("findAllPatientsByPsychologist").setParameter("dniPsychologist", dniPsychologist).getResultList();
        } catch (Exception e) {
            throw new UserException(e.getMessage());
        }
        return users;
    }

}
