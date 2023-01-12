/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
import exceptions.UserException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import userService.UserInterface;

/**
 *
 * @author unaibAndLeire
 */
@Path("entities.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @EJB
    private UserInterface userEJB;
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());

    public UserFacadeREST(Class<User> entityClass) {
        super(entityClass);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User entity) {
        try {
            userEJB.createUser(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editUser(@PathParam("dni") String dni, User entity) {
        try {
            userEJB.updateUser(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{dni}")
    public void removeUser(@PathParam("dni") String dni) {
        try {
            userEJB.deleteUser(dni);
        } catch (DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            users = userEJB.findAllUsers();
        } catch (UserException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return users;
    }

    @GET
    @Path("{dni}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUsersByDni(@PathParam("dni") String dni) {
        User user = null;
        try {
            user = userEJB.findUserByDni(dni);
        } catch (UserException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return user;
    }

    @GET
    @Path("findPatientsByPsychologist/{dniPsychologist}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAllPatientsByPsychologist(@PathParam("dniPsychologist") String dniPsychologist) {
        List<User> users = null;
        try {
            users = userEJB.findAllPatientsByPsychologist(dniPsychologist);
        } catch (UserException ex) {
            Logger.getLogger(UserFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
